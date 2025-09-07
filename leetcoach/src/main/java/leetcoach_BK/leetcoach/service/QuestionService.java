package leetcoach_BK.leetcoach.service;

import java.util.List;
import org.springframework.stereotype.Service;

import leetcoach_BK.leetcoach.model.Questions;
import leetcoach_BK.leetcoach.repositry.QuestionsRepo;

@Service
public class QuestionService {

    private final QuestionsRepo questionsRepo;

    public QuestionService(QuestionsRepo questionsRepo) {
        this.questionsRepo = questionsRepo;
    }

    public List<Questions> allQuestions() {

        return questionsRepo.findAll();
    }

    public List<Questions> byTopic(String topic) {
        return questionsRepo.findByTopics(topic);
    }

    public Questions byId(String Id) {

        return questionsRepo.findById(Id).orElseThrow(() -> new RuntimeException("The qution id is invalid!" + Id));

    }

    public List<Questions> getByTags(String tag) {

        return questionsRepo.findByTag(tag);
    }

    public List<Questions> difQuestions(String difficult) {
        return questionsRepo.findByDifficulty(difficult);
    }
}