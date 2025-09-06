package leetcoach_BK.leetcoach.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import leetcoach_BK.leetcoach.model.Users;
import leetcoach_BK.leetcoach.repositry.UserRepository;


@Service
public class UserService {
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;

public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder)
{
 this.userRepository=userRepository;
this.passwordEncoder=passwordEncoder;
  }


 public String signup(String FirstName,String LastName,String UserName,String Password,String Role )
{

        if (userRepository.existsByUserName(UserName)) {
            return "Username already exists!";
      }
      String encodedPassword = passwordEncoder.encode(Password);
   Users user = new Users(FirstName,LastName,UserName,encodedPassword, Role.toUpperCase());
   userRepository.save(user);
    return "User registered successfully!";
     
 }


 public String login(String UserName,String Password)
{
 Users user = userRepository.findByUserName(UserName)
.orElse(null);
if (user == null) {
return "User not found!";
}
if (!passwordEncoder.matches(Password, user.getPassword())) {
 return "Invalid password!";
}
 return "Login successful!";
   }
}



 
     
       


   




// }
