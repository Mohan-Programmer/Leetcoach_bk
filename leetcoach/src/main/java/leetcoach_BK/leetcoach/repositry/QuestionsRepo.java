package leetcoach_BK.leetcoach.repositry;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import leetcoach_BK.leetcoach.model.QuestionTitleProjection;
import leetcoach_BK.leetcoach.model.Questions;

public interface QuestionsRepo extends MongoRepository<Questions,String>{
   List<Questions> findByTopics(String topic);
   List<Questions> findByDifficulty(String difficult);
 
   @Query(value = "{}", fields = "{ 'question_id': 1, 'title': 1, 'difficulty': 1 }")
   List<QuestionTitleProjection> findAllBy();

}