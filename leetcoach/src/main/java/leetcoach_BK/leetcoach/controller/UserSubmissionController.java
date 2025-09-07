package leetcoach_BK.leetcoach.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import leetcoach_BK.leetcoach.model.UserSubmission;
import leetcoach_BK.leetcoach.service.UserSubmissionService;

@RestController
@RequestMapping("/submissions")
public class UserSubmissionController {

    private final UserSubmissionService userSubmissionService;

    public UserSubmissionController(UserSubmissionService userSubmissionService) {
        this.userSubmissionService = userSubmissionService;
    }

    // to get specific submission
    @GetMapping("/code/{userid}/{questionid}")
    public UserSubmission getUserCode(@PathVariable("userid") String userId,
            @PathVariable("questionid") String questionId) {
        return userSubmissionService.getByquestion(userId, questionId);
    }

    // to save answer

    @PostMapping("/submit")
    public UserSubmission saveCode(@RequestBody UserSubmission submission) {
        return userSubmissionService.saveAnswer(submission);

    }

    // to get all question

    @GetMapping("/subquestion")
    public List<UserSubmission> getAllSubcode() {
        return userSubmissionService.getAllSub();
    }

    // get all solved qestions id
    @GetMapping("/solved/{userid}")
    public List<String> getAllSovedId(@PathVariable String userid) {
        return userSubmissionService.getSolvedQuestionIds(userid);
    }

}
