package leetcoach_BK.leetcoach.service;

import java.util.List;

import org.springframework.stereotype.Service;

import leetcoach_BK.leetcoach.model.UserSubmission;
import leetcoach_BK.leetcoach.repositry.UserSubmissionRepo;

@Service
public class UserSubmissionService {
    
    private final UserSubmissionRepo userSubmissionRepo;

    public UserSubmissionService(UserSubmissionRepo userSubmissionRepo)
    {
            this.userSubmissionRepo=userSubmissionRepo;

    }

    public List<UserSubmission> getByquestion(String question_id)
    {
        return  userSubmissionRepo.findByQuestionId(question_id);
    }

 public UserSubmission saveAnswer(UserSubmission submission)
 {
      return userSubmissionRepo.save(submission);   
 }

 public List<UserSubmission> getAllSub()
 {
    return userSubmissionRepo.findAll();
 }

}
