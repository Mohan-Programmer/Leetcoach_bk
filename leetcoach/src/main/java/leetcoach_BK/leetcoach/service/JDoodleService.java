package leetcoach_BK.leetcoach.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JDoodleService {

    private static final String JDoodle_URL = "https://api.jdoodle.com/v1/execute";
    private static final String CLIENT_ID = "e2d82e13a87fdf02387e7186c4940356";       // your JDoodle client id
    private static final String CLIENT_SECRET = "aed6b6a130bca0a33920a77d7bd8ff63a2b678d862502e5c5a40d7c34aea3d68"; // your JDoodle secret

    /**
     * Evaluate code against multiple test cases
     */
    public EvaluationResult evaluate(String code, String language, List<String> inputs, List<String> expectedOutputs) {
        if (inputs.size() != expectedOutputs.size()) {
            throw new IllegalArgumentException("Number of inputs and expected outputs must match");
        }

        boolean allPassed = true;
        double maxRuntime = 0;
        double maxMemory = 0;
        StringBuilder combinedOutput = new StringBuilder();

        for (int i = 0; i < inputs.size(); i++) {
            EvaluationResult result = evaluateSingle(code, language, inputs.get(i), expectedOutputs.get(i));

            allPassed &= result.isCorrect;
            maxRuntime = Math.max(maxRuntime, result.runtime);
            maxMemory = Math.max(maxMemory, result.memory);
            combinedOutput.append("Test case ").append(i + 1).append(" output:\n")
                          .append(result.output).append("\n");
        }

        return new EvaluationResult(allPassed, maxRuntime, maxMemory, combinedOutput.toString());
    }

    /**
     * Evaluate a single test case
     */
    @SuppressWarnings("unchecked")
    private EvaluationResult evaluateSingle(String code, String language, String input, String expectedOutput) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> request = new HashMap<>();
        request.put("clientId", CLIENT_ID);
        request.put("clientSecret", CLIENT_SECRET);
        request.put("script", code);
        request.put("stdin", input);
        request.put("language", language);
        request.put("versionIndex", "0");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        Map<String, Object> response;
        try {
            response = restTemplate.postForObject(JDoodle_URL, entity, Map.class);
        } catch (RestClientException e) {
            throw new RuntimeException("JDoodle API request failed: " + e.getMessage(), e);
        }

        if (response == null) {
            throw new RuntimeException("JDoodle API returned null response");
        }

        String output = response.get("output") != null ? response.get("output").toString() : "";
        double cpuTime = response.get("cpuTime") != null ? Double.parseDouble(response.get("cpuTime").toString()) : 0;
        double memory = response.get("memory") != null ? Double.parseDouble(response.get("memory").toString()) : 0;
        boolean isCorrect = output.replace("\r", "").trim().equals(expectedOutput.replace("\r", "").trim());

        return new EvaluationResult(isCorrect, cpuTime, memory, output);
    }

    /**
     * Result holder class
     */
    public static class EvaluationResult {
        public boolean isCorrect;
        public double runtime;
        public double memory;
        public String output;

        public EvaluationResult(boolean isCorrect, double runtime, double memory, String output) {
            this.isCorrect = isCorrect;
            this.runtime = runtime;
            this.memory = memory;
            this.output = output;
        }
    }
}
