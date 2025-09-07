package leetcoach_BK.leetcoach.repositry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import leetcoach_BK.leetcoach.model.UserSubmission;

public interface UserSubmissionRepo extends MongoRepository<UserSubmission, String> {
   Optional<UserSubmission> findByUserIdAndQuestionId(String userid, String question_id);

   List<UserSubmission> findByUserId(String questionId);
}
