package repositry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import model.Questions;

public interface QuestionsRepo extends MongoRepository<Questions,String>{
   List<Questions> findByTopic(String topic);
   Optional<Questions> findById(String Id);

}