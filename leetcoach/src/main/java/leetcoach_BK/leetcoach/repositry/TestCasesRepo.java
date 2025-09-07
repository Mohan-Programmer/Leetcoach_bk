package leetcoach_BK.leetcoach.repositry;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import leetcoach_BK.leetcoach.model.TestCases;

public interface TestCasesRepo extends MongoRepository<TestCases, String> {
    List<TestCases> findByQuestionId(String questionId);
}
