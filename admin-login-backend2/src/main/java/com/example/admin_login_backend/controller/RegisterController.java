package com.example.admin_login_backend.controller;

import com.example.admin_login_backend.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "")
public class RegisterController {
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("useradmin").document(user.getEmail()).set(user);
        String updateTime = collectionsApiFuture.get().getUpdateTime().toString();

        Map<String, String> response = new HashMap<>();
        response.put("updateTime", updateTime);

        return ResponseEntity.ok(response);
    }
}