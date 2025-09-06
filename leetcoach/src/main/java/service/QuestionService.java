package service;

import java.util.List;
import org.springframework.stereotype.Service;
import model.Questions;
import repositry.QuestionsRepo;

@Service
public class QuestionService {

private final QuestionsRepo questionsRepo;

public QuestionService(QuestionsRepo questionsRepo)
{
    this.questionsRepo=questionsRepo;
}

public List<Questions> allQuestions(){

    return questionsRepo.findAll();
}

public List<Questions> byTopic(String topic)
{
    return  questionsRepo.findByTopic(topic);
}

public Questions byId(String Id)
{

    return questionsRepo.findById(Id).orElseThrow(()-> new RuntimeException("The qution id is invalid!"+Id));

}
}