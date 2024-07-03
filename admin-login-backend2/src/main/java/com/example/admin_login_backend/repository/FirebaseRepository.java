package com.example.admin_login_backend.repository;

import com.example.admin_login_backend.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class FirebaseRepository {

    private final Firestore firestore;

    @Autowired
    public FirebaseRepository(FirebaseApp firebaseApp) {
        this.firestore = FirestoreClient.getFirestore(firebaseApp);
    }

    // ======== Autenticación y Usuarios ========

    public ApiFuture<WriteResult> setDocument(String path, Object data) {
        DocumentReference docRef = firestore.document(path);
        return docRef.set(data);
    }

    public List<QueryDocumentSnapshot> getAllDocuments(String collection) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = firestore.collection(collection);
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        QuerySnapshot querySnapshot = future.get();
        return querySnapshot.getDocuments();
    }

    public DocumentSnapshot getDocument(String path) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.document(path);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        return future.get();
    }

    // ======== Usuarios ========
    public List<User> getUsers() throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> snapshots = getAllDocuments("useradmin");
        return snapshots.stream().map(snapshot -> snapshot.toObject(User.class)).collect(Collectors.toList());
    }

    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
        CollectionReference users = firestore.collection("useradmin");
        Query query = users.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        return documents.isEmpty() ? null : documents.get(0).toObject(User.class);
    }

    public ApiFuture<WriteResult> saveUser(User user) {
        String path = "useradmin/" + user.getEmail();
        return setDocument(path, user);
    }



}
