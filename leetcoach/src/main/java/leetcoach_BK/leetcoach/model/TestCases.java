package leetcoach_BK.leetcoach.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="testcases")
public class TestCases {

 @Id
    private String id; // MongoDB document ID

    private String questionId; // Link to your question

    private List<TestCase> testcases; // List of structured test cases

}
