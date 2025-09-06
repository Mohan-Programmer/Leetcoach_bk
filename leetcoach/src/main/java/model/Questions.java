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
@Document(collection = "questions")
public class Questions {

    @Id
    private String question_id;

   private String title;
   private String description;
   private String difficulty;
   private String topics;
   private String constrains;
   private String example;
   private String solutions;
   private String hints;
   private String tag;

   public Questions(String title, String description,String difficulty,String topics,String constrains,String example,String solutions,String hints,String tag){
            
    this.title=title;
    this.description=description;
    this.difficulty=difficulty;
    this.topics=topics;
    this.constrains=constrains;
    this.example=example;
    this.solutions=solutions;
    this.hints=hints;
    this.tag=tag;

   }

}
