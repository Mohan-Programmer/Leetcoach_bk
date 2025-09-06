package model;

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
@Document(collation = "questions")
public class Questions {

    @Id
    private String question_id;

   private String title;
   private String description;
   private String difficulty;
   private String topics;
   private String example;
   private String solutions;
   private String hints;

   public Questions(String title, String description,String difficulty,String topics,String example,String solutions,String hints){
            
    this.title=title;
    this.description=description;
    this.difficulty=difficulty;
    this.topics=topics;
    this.example=example;
    this.solutions=solutions;
    this.hints=hints;

   }

}
