package leetcoach_BK.leetcoach.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import leetcoach_BK.leetcoach.dto.SubmissionRequest;
import leetcoach_BK.leetcoach.model.UserSubmission;
import leetcoach_BK.leetcoach.service.UserSubmissionService;

@RestController
@RequestMapping("/submissions")
public class UserSubmissionController {

    private final UserSubmissionService userSubmissionService;

    public UserSubmissionController(UserSubmissionService userSubmissionService) {
        this.userSubmissionService = userSubmissionService;
    }

    // Get a specific submission
    @GetMapping("/code/{userid}/{questionid}")
    public UserSubmission getUserCode(@PathVariable("userid") String userId,
                                      @PathVariable("questionid") String questionId) {
        return userSubmissionService.getByQuestion(userId, questionId);
    }

    // Save submission with multi-test-case JDoodle evaluation + AI feedback
    @PostMapping("/submit")
    public UserSubmission saveCode(@RequestBody SubmissionRequest request) {
        UserSubmission submission = request.toUserSubmission();
        // Pass the problem statement for AI feedback
        return userSubmissionService.saveAnswer(
                submission,
                request.getInputs(),
                request.getExpectedOutputs(),
                request.getProblemStatement()
        );
    }

    // Get all submissions
    @GetMapping("/subquestion")
    public List<UserSubmission> getAllSubcode() {
        return userSubmissionService.getAllSub();
    }

    // Get all solved question IDs for a user
    @GetMapping("/solved/{userid}")
    public List<String> getAllSolvedId(@PathVariable String userid) {
        return userSubmissionService.getSolvedQuestionIds(userid);
    }
}
