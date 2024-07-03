package com.example.admin_login_backend.controller;

import com.example.admin_login_backend.entity.DisabledSlot;
import com.example.admin_login_backend.entity.DisabledUser;
import com.example.admin_login_backend.service.DisabledUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/disabledUser")
public class DisabledUserController {
    @Autowired
    private DisabledUserService disabledUserService;

    @GetMapping
    public List<DisabledUser> getAllDisabledUser() throws ExecutionException, InterruptedException {
        return disabledUserService.getAllDisableUser();
    }
    @PutMapping("/enable")
    public ResponseEntity<String> updateDisabledSlot(@RequestParam String email) throws ExecutionException, InterruptedException {
        String result = disabledUserService.updateDisabledUserByEmail(email);
        return ResponseEntity.ok(result);
    }
}
