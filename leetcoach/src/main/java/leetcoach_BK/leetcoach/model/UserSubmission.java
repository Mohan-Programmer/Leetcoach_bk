package leetcoach_BK.leetcoach.model;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "usersubmission")
public class UserSubmission {

    private String userId; // to connect question with user id
    private String questionId;
    private String language; // programming language
    private String code; // user answer code
    private boolean isCorrect; // the code is correct or not
    private String status; // accept,reject,wrong
    private double runtime;
    private double memory;
    private String aifeedback;
    private LocalDateTime submittedAt;

    public UserSubmission(String userId, String questionId, String code, String language,
            boolean isCorrect, String status, double runtime, double memory) {
        this.userId = userId;
        this.questionId = questionId;
        this.code = code; // ✅ Stores the full code
        this.language = language;
        this.isCorrect = isCorrect;
        this.status = status;
        this.runtime = runtime;
        this.memory = memory;
        this.submittedAt = LocalDateTime.now();
    }

}
