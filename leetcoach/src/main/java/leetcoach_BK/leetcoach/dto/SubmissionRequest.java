package leetcoach_BK.leetcoach.dto;

import java.util.List;
import leetcoach_BK.leetcoach.model.UserSubmission;

// ---------------- SubmissionRequest DTO ----------------
public class SubmissionRequest {
    private String userId;
    private String questionId;
    private String language;
    private String code;
    private List<String> inputs;           // multiple test case inputs
    private List<String> expectedOutputs;  // multiple expected outputs
    private String problemStatement;       // problem/question text for AI feedback

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

    public String getProblemStatement() { return problemStatement; }
    public void setProblemStatement(String problemStatement) { this.problemStatement = problemStatement; }
}
