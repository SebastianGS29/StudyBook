package com.example.admin_login_backend.controller;

import com.example.admin_login_backend.model.User;
import com.example.admin_login_backend.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "")
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @Autowired
    public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @PutMapping("/update")
    public String updateForgotPassword(@RequestBody User user) throws InterruptedException, ExecutionException {
        return forgotPasswordService.actualizacionClave(user);
    }
}
