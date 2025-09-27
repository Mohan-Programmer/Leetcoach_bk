package leetcoach_BK.leetcoach.repositry;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import leetcoach_BK.leetcoach.model.Questions;

public interface QuestionsRepo extends MongoRepository<Questions, String> {
    List<Questions> findByTopicsContaining(String topic);
    List<Questions> findByDifficulty(String difficulty);
    List<Questions> findByTagContaining(String tag);
}
