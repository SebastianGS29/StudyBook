package com.example.admin_login_backend.service;

import com.example.admin_login_backend.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
@Service
public class ForgotPasswordService {

    public String actualizacionClave(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        // Verifica que el usuario exista antes de actualizar
        User existingUser = dbFirestore.collection("useradmin").document(user.getEmail()).get().get().toObject(User.class);
        if (existingUser == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Actualiza solo la contraseña
        Map<String, Object> updates = new HashMap<>();
        updates.put("clave", user.getClave());

        ApiFuture<WriteResult> updateResult = dbFirestore.collection("useradmin").document(user.getEmail()).update(updates);
        return updateResult.get().getUpdateTime().toString();
    }
}
