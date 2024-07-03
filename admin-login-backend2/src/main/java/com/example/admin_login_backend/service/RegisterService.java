package com.example.admin_login_backend.service;

import com.example.admin_login_backend.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class RegisterService {

    public String registerUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("useradmin").document(user.getEmail()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}