package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {


    private AuthService authService;

   public AuthController(AuthService authService){
            this.authService=authService;
   }

//end point for signup
@PostMapping("signup")
public String signup(@RequestParam String FirstName,
                     @RequestParam String LastName,
                     @RequestParam String UserName,
                     @RequestParam String Password,
                     @RequestParam String Role)
{

    return authService.signup(FirstName, LastName, UserName, Password, Role);
}


//end ponit for login
@PostMapping("login")
public String login(@RequestParam String UserName,@RequestParam String Password){
    return authService.login(UserName, Password);
}
}
