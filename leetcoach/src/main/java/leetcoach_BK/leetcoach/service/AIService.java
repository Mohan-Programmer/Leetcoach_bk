package leetcoach_BK.leetcoach.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
public class AIService {

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String OPENAI_API_KEY = "your_openai_api_key_here"; // Replace with your key

    @SuppressWarnings("unchecked")
    public String getCodeFeedback(String code, String language, String problemStatement, boolean passed) {
        String prompt = "User submitted code:\n" + code +
                        "\nLanguage: " + language +
                        "\nProblem: " + problemStatement +
                        "\nResult: " + (passed ? "Passed" : "Failed") +
                        "\nProvide hints, explanation, or improvements in simple English.";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPENAI_API_KEY);

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4"); // or gpt-3.5-turbo
        body.put("messages", new Map[]{message});
        body.put("max_tokens", 200);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        Map<String, Object> response = restTemplate.postForObject(OPENAI_API_URL, entity, Map.class);
        if (response != null) {
            try {
                Map<String, Object> choice = ((List<Map<String, Object>>) response.get("choices")).get(0);
                Map<String, Object> messageMap = (Map<String, Object>) choice.get("message");
                return messageMap.get("content").toString();
            } catch (Exception e) {
                return "AI feedback unavailable";
            }
        }
        return "AI feedback unavailable";
    }
}
