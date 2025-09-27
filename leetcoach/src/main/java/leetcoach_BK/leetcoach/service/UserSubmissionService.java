package leetcoach_BK.leetcoach.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import leetcoach_BK.leetcoach.model.UserSubmission;
import leetcoach_BK.leetcoach.repositry.UserSubmissionRepo;

@Service
public class UserSubmissionService {

    private final UserSubmissionRepo userSubmissionRepo;
    private final JDoodleService jdoodleService;

    public UserSubmissionService(UserSubmissionRepo userSubmissionRepo, JDoodleService jdoodleService) {
        this.userSubmissionRepo = userSubmissionRepo;
        this.jdoodleService = jdoodleService;
    }

    // Get a submitted code for an individual question
    public UserSubmission getByQuestion(String userId, String questionId) {
        return userSubmissionRepo.findByUserIdAndQuestionId(userId, questionId).orElse(null);
    }

    /**
     * Save or update a user submission with multi-test-case evaluation
     *
     * @param submission UserSubmission object
     * @param inputs List of inputs
     * @param expectedOutputs List of expected outputs
     * @return Saved UserSubmission with evaluation results
     */
    public UserSubmission saveAnswer(UserSubmission submission, List<String> inputs, List<String> expectedOutputs) {
        // 1. Evaluate using JDoodle for all test cases
        JDoodleService.EvaluationResult result = jdoodleService.evaluate(
                submission.getCode(),
                submission.getLanguage(),
                inputs,
                expectedOutputs
        );

        // 2. Update submission fields based on evaluation
        submission.setCorrect(result.isCorrect);
        submission.setStatus(result.isCorrect ? "Accepted" : "Wrong");
        submission.setRuntime(result.runtime);
        submission.setMemory(result.memory);
        submission.setSubmittedAt(LocalDateTime.now());

        // 3. Check if submission already exists
        Optional<UserSubmission> existing = userSubmissionRepo.findByUserIdAndQuestionId(
                submission.getUserId(),
                submission.getQuestionId()
        );

        if (existing.isPresent()) {
            // Update the existing submission
            UserSubmission old = existing.get();
            old.setCode(submission.getCode());
            old.setStatus(submission.getStatus());
            old.setCorrect(submission.isCorrect());
            old.setRuntime(submission.getRuntime());
            old.setMemory(submission.getMemory());
            old.setSubmittedAt(submission.getSubmittedAt());
            old.setLanguage(submission.getLanguage());
            return userSubmissionRepo.save(old);
        } else {
            // Save new submission
            return userSubmissionRepo.save(submission);
        }
    }

    // Get all submitted code
    public List<UserSubmission> getAllSub() {
        return userSubmissionRepo.findAll();
    }

    // Get solved question IDs for a user
    public List<String> getSolvedQuestionIds(String userId) {
        return userSubmissionRepo.findByUserId(userId).stream()
                .filter(sub -> sub.isCorrect() || "Accepted".equalsIgnoreCase(sub.getStatus()))
                .map(UserSubmission::getQuestionId)
                .distinct()
                .toList();
    }
}
