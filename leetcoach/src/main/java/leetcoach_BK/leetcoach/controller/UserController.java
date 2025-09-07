package leetcoach_BK.leetcoach.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import leetcoach_BK.leetcoach.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Signup endpoint (accepts JSON)
    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody Map<String, String> request) {
        String message = userService.signup(
                request.get("firstName"),
                request.get("lastName"),
                request.get("userName"),
                request.get("password"),
                request.get("role"));
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", message));
    }

    // Login endpoint (accepts JSON)
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        String message = userService.login(
                request.get("userName"),
                request.get("password"));
        return ResponseEntity.ok(Map.of("message", message));
    }
}
