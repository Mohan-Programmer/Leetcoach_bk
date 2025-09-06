package leetcoach_BK.leetcoach.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Document(collection = "users")
public class Users{

@Id
private String userId;

private String FirstName;
private String LastName;
private String UserName;
private String Password;
private String Role;


public Users(String FirstName,String LastName,String UserName,String Password,String Role){
            this.FirstName=FirstName;
            this.LastName=LastName;
            this.UserName=UserName;
            this.Password=Password;
            this.Role=Role;
}




}
