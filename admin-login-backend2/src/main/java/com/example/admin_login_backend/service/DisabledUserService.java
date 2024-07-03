package com.example.admin_login_backend.service;

import com.example.admin_login_backend.entity.DisabledUser;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class DisabledUserService {

    public static final String COLLECTION_NAME = "users";

    public List<DisabledUser> getAllDisableUser() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference disabledSlotsCollection = dbFirestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> querySnapshot = disabledSlotsCollection.whereEqualTo("disabled", true).get();

        return querySnapshot.get().getDocuments().stream()
                .map(document -> document.toObject(DisabledUser.class))
                .collect(Collectors.toList());
    }

    public String updateDisabledUserByEmail(String email) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference usersCollection = dbFirestore.collection(COLLECTION_NAME);

        ApiFuture<QuerySnapshot> querySnapshot = usersCollection.whereEqualTo("email", email).get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        if (documents.isEmpty()) {
            return "No user found with email: " + email;
        }

        DocumentReference documentReference = documents.get(0).getReference();
        ApiFuture<WriteResult> writeResult = documentReference.update("disabled", false);
        return "Usuario con el correo " + email + " fue perdonado";
    }
}