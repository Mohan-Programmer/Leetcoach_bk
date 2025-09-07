package leetcoach_BK.leetcoach.service;

import java.util.List;

import org.springframework.stereotype.Service;

import leetcoach_BK.leetcoach.model.TestCases;
import leetcoach_BK.leetcoach.repositry.TestCasesRepo;

@Service
public class TestCasesService {
    private final TestCasesRepo testCasesRepo;

    public TestCasesService(TestCasesRepo testCasesRepo){

        this.testCasesRepo=testCasesRepo;
    }


    public List<TestCases> getByQuestionId(String questionId)
    {
        return testCasesRepo.findByQuestionId(questionId);
    }

}
