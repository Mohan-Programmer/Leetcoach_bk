package leetcoach_BK.leetcoach.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import leetcoach_BK.leetcoach.model.UserSubmission;
import leetcoach_BK.leetcoach.service.UserSubmissionService;

@RestController
public class UserSubmissionController {

private final UserSubmissionService userSubmissionService;

public UserSubmissionController(UserSubmissionService userSubmissionService)
{
    this.userSubmissionService=userSubmissionService;
}

@GetMapping("/question/code/{question_id}")
public List<UserSubmission> getUserCode(@PathVariable String question_id)
{
    return userSubmissionService.getByquestion(question_id);
}

@PostMapping
public UserSubmission saveCode(@RequestBody UserSubmission submission)
{
   return userSubmissionService.saveAnswer(submission);

}

@GetMapping("/subquestion")
public List<UserSubmission> getAllSubcode()
{
    return userSubmissionService.getAllSub();
}

}
