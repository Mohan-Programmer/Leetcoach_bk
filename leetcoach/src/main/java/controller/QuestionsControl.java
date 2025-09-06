package controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Questions;
import service.QuestionService;

@RestController
public class QuestionsControl {

private final QuestionService questionService;

public QuestionsControl(QuestionService questionService)
{
    this.questionService=questionService;
}

@GetMapping("/questions")
public List<Questions> questions(){
    return questionService.allQuestions();
}

@GetMapping("/questions/{topic}")
public List<Questions> bytoppic(@PathVariable String topic)
{
    return questionService.byTopic(topic);
}
@GetMapping("/qustions/{Id}")
public Questions byQuestionsId(@PathVariable String Id)
{
    return questionService.byId(Id);
}

}
