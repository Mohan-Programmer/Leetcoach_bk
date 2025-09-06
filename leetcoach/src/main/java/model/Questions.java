package model;


import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "questions")
public class Questions {

    @Id
    private String question_id;
    
    private String title;
    private String description;
    private String difficulty;
    private List<String> topics;
    private List<String> constraints;
    private List<Map<String, String>> examples; // each example has input & output
    private Map<String, String> solutions; // code, language, explanation
    private List<String> hints;
    private List<String> tag;

   public Questions(String title, String description,String difficulty,List<String> topics,List<String> constraints,List<Map<String,String>> examples,Map<String,String> solutions,List<String> hints,List<String> tag){
            
    this.title=title;
    this.description=description;
    this.difficulty=difficulty;
    this.topics=topics;
    this.constraints=constraints;
    this.examples=examples;
    this.solutions=solutions;
    this.hints=hints;
    this.tag=tag;

   }

}
