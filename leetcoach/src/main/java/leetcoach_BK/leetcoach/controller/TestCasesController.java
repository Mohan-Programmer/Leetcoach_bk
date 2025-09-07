package leetcoach_BK.leetcoach.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import leetcoach_BK.leetcoach.model.TestCases;
import leetcoach_BK.leetcoach.service.TestCasesService;

@RestController
public class TestCasesController {

    private final TestCasesService testCasesService;

    public TestCasesController(TestCasesService testCasesService){

        this.testCasesService=testCasesService;
    }

    @GetMapping("/testcases/{questionid}")
    public List<TestCases> getTestCases(@PathVariable String questionid)
    {

        return testCasesService.getByQuestionId(questionid);
    }

}
