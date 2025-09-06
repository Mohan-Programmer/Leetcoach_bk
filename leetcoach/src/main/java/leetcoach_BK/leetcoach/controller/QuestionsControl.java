package leetcoach_BK.leetcoach.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import leetcoach_BK.leetcoach.model.QuestionTitleProjection;
import leetcoach_BK.leetcoach.model.Questions;
import leetcoach_BK.leetcoach.service.QuestionService;

@RestController
public class QuestionsControl {

private final QuestionService questionService;


public QuestionsControl(QuestionService questionService)
{
    this.questionService=questionService;
}
@GetMapping("/questions/titles")
public List<QuestionTitleProjection> getTitles()
{
    return questionService.getAllTitles();
}

@GetMapping("/questions")
public List<Questions> questions(){
    return questionService.allQuestions();
}

@GetMapping("/questions/topic/{topic}")
public List<Questions> bytoppic(@PathVariable String topic)
{
    return questionService.byTopic(topic);
}

@GetMapping("/questions/tag/{tag}")
public List<Questions> getBytag(@PathVariable String tag)
{
return questionService.getByTags(tag);
}

@GetMapping("/questions/id/{id}")
public Questions byQuestionsId(@PathVariable String id)
{
    return questionService.byId(id);
}

@GetMapping("/questions/dif/{dif}")
public List<Questions> bydiff(@PathVariable String dif)
{
    return questionService.difQuestions(dif);
}

}
