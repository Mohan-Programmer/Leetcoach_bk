package leetcoach_BK.leetcoach.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import leetcoach_BK.leetcoach.model.EvaluationResult;
import leetcoach_BK.leetcoach.model.UserSubmission;
import leetcoach_BK.leetcoach.repositry.UserSubmissionRepo;

@Service
public class UserSubmissionService {

    private final UserSubmissionRepo userSubmissionRepo;
    private final JDoodleService jdoodleService;
    private final AIService aiService;

    public UserSubmissionService(UserSubmissionRepo userSubmissionRepo, JDoodleService jdoodleService, AIService aiService) {
        this.userSubmissionRepo = userSubmissionRepo;
        this.jdoodleService = jdoodleService;
        this.aiService = aiService;
    }

    // Get a submitted code for an individual question
    public UserSubmission getByQuestion(String userId, String questionId) {
        return userSubmissionRepo.findByUserIdAndQuestionId(userId, questionId).orElse(null);
    }

    /**
     * Save or update a user submission with multi-test-case evaluation and AI feedback
     *
     * @param submission      UserSubmission object
     * @param inputs          List of inputs for test cases
     * @param expectedOutputs List of expected outputs for test cases
     * @param problemStatement The text of the problem/question
     * @return Saved UserSubmission with evaluation results and AI feedback
     */
    public UserSubmission saveAnswer(UserSubmission submission, List<String> inputs, List<String> expectedOutputs, String problemStatement) {
        // 1. Evaluate using JDoodle for all test cases
        EvaluationResult result = jdoodleService.evaluate(
                submission.getCode(),
                submission.getLanguage(),
                inputs,
                expectedOutputs
        );

        // 2. Update submission fields based on JDoodle evaluation
        submission.setCorrect(result.isCorrect);
        submission.setStatus(result.isCorrect ? "Accepted" : "Wrong");
        submission.setRuntime(result.runtime);
        submission.setMemory(result.memory);
        submission.setSubmittedAt(LocalDateTime.now());

        // 3. Generate AI feedback
        String aiFeedback = aiService.getCodeFeedback(
                submission.getCode(),
                submission.getLanguage(),
                problemStatement,
                result.isCorrect
        );
        submission.setAifeedback(aiFeedback);

        // 4. Save or update submission in DB
        Optional<UserSubmission> existing = userSubmissionRepo.findByUserIdAndQuestionId(
                submission.getUserId(),
                submission.getQuestionId()
        );

        if (existing.isPresent()) {
            UserSubmission old = existing.get();
            old.setCode(submission.getCode());
            old.setStatus(submission.getStatus());
            old.setCorrect(submission.isCorrect());
            old.setRuntime(submission.getRuntime());
            old.setMemory(submission.getMemory());
            old.setSubmittedAt(submission.getSubmittedAt());
            old.setLanguage(submission.getLanguage());
            old.setAifeedback(submission.getAifeedback());
            return userSubmissionRepo.save(old);
        } else {
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
