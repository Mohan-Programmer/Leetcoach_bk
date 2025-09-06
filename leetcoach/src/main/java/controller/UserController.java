package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.UserService;


@RestController
@RequestMapping("/auth")
public class UserController {


    private final UserService userService;

   public UserController(UserService userService){
            this.userService=userService;
   }

//end point for signup
@PostMapping("signup")
public String signup(@RequestParam String FirstName,
                     @RequestParam String LastName,
                     @RequestParam String UserName,
                     @RequestParam String Password,
                     @RequestParam String Role)
{

    return userService.signup(FirstName, LastName, UserName, Password, Role);
}


//end ponit for login
@PostMapping("login")
public String login(@RequestParam String UserName,@RequestParam String Password){
    return userService.login(UserName, Password);
}
}
