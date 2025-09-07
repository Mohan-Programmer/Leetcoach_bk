package leetcoach_BK.leetcoach.service;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import leetcoach_BK.leetcoach.model.UserSubmission;
import leetcoach_BK.leetcoach.repositry.UserSubmissionRepo;

@Service
public class UserSubmissionService {

    private final UserSubmissionRepo userSubmissionRepo;

    public UserSubmissionService(UserSubmissionRepo userSubmissionRepo) {
        this.userSubmissionRepo = userSubmissionRepo;

    }

    // to get a submitted code individual
    public UserSubmission getByquestion(String userid, String question_id) {
        return userSubmissionRepo.findByUserIdAndQuestionId(userid, question_id).orElse(null);
    }

    // to save the user submission
    public UserSubmission saveAnswer(UserSubmission submission) {
        Optional<UserSubmission> existing = userSubmissionRepo.findByUserIdAndQuestionId(
                submission.getUserId(),
                submission.getQuestionId());

        if (existing.isPresent()) {
            // Update the existing submission
            UserSubmission oldSubmission = existing.get();
            oldSubmission.setCode(submission.getCode()); // Update code
            oldSubmission.setStatus(submission.getStatus()); // Update status
            oldSubmission.setSubmittedAt(submission.getSubmittedAt()); // Update timestamp if you have it
            oldSubmission.setLanguage(submission.getLanguage());
            oldSubmission.setMemory(submission.getMemory());
            oldSubmission.setRuntime(submission.getRuntime());
            return userSubmissionRepo.save(oldSubmission);
        } else {
            // Save a new one
            return userSubmissionRepo.save(submission);
        }
    }

    // to get all submitted code
    public List<UserSubmission> getAllSub() {
        return userSubmissionRepo.findAll();
    }

    // to get a solved question id's
    public List<String> getSolvedQuestionIds(String userId) {
        return userSubmissionRepo.findByUserId(userId).stream()
                .filter(sub -> sub.isCorrect() || "Accepted".equalsIgnoreCase(sub.getStatus()))
                .map(UserSubmission::getQuestionId)
                .distinct()
                .toList();
    }
}
