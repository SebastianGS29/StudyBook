package com.example.admin_login_backend.controller;
import com.example.admin_login_backend.model.User;
import com.example.admin_login_backend.service.UserService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/useradmin")
@CrossOrigin(origins = "")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) throws ExecutionException, InterruptedException {
        return userService.getUserByEmail(email);
    }
    @GetMapping("/emails")
    public List<String> getAllEmails() throws ExecutionException, InterruptedException {
        return userService.getAllEmails();
    }
    @PostMapping
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

}