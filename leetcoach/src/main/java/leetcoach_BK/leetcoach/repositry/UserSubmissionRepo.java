package leetcoach_BK.leetcoach.repositry;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import leetcoach_BK.leetcoach.model.UserSubmission;

public interface UserSubmissionRepo extends MongoRepository<UserSubmission,String>{
   List< UserSubmission > findByQuestionId(String question_id);
}
