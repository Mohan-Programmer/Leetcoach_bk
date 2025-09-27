package leetcoach_BK.leetcoach.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import leetcoach_BK.leetcoach.model.UserSubmission;
import leetcoach_BK.leetcoach.service.UserSubmissionService;

@RestController
@RequestMapping("/submissions")
public class UserSubmissionController {

    private final UserSubmissionService userSubmissionService;

    public UserSubmissionController(UserSubmissionService userSubmissionService) {
        this.userSubmissionService = userSubmissionService;
    }

    // Get a specific submission
    @GetMapping("/code/{userid}/{questionid}")
    public UserSubmission getUserCode(@PathVariable("userid") String userId,
                                      @PathVariable("questionid") String questionId) {
        return userSubmissionService.getByQuestion(userId, questionId);
    }

    // Save submission with multi-test-case JDoodle evaluation
    @PostMapping("/submit")
    public UserSubmission saveCode(@RequestBody SubmissionRequest request) {
        UserSubmission submission = request.toUserSubmission();
        return userSubmissionService.saveAnswer(submission, request.getInputs(), request.getExpectedOutputs());
    }

    // Get all submissions
    @GetMapping("/subquestion")
    public List<UserSubmission> getAllSubcode() {
        return userSubmissionService.getAllSub();
    }

    // Get all solved question IDs for a user
    @GetMapping("/solved/{userid}")
    public List<String> getAllSolvedId(@PathVariable String userid) {
        return userSubmissionService.getSolvedQuestionIds(userid);
    }

    // ---------------- SubmissionRequest DTO ----------------
    public static class SubmissionRequest {
        private String userId;
        private String questionId;
        private String language;
        private String code;
        private List<String> inputs;           // multiple test case inputs
        private List<String> expectedOutputs;  // multiple expected outputs

        // Convert DTO to UserSubmission
        public UserSubmission toUserSubmission() {
            return new UserSubmission(
                    userId,
                    questionId,
                    code,
                    language,
                    false,
                    "Pending",
                    0, 0
            );
        }

        // Getters and setters
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
        public String getQuestionId() { return questionId; }
        public void setQuestionId(String questionId) { this.questionId = questionId; }
        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public List<String> getInputs() { return inputs; }
        public void setInputs(List<String> inputs) { this.inputs = inputs; }
        public List<String> getExpectedOutputs() { return expectedOutputs; }
        public void setExpectedOutputs(List<String> expectedOutputs) { this.expectedOutputs = expectedOutputs; }
    }
}
