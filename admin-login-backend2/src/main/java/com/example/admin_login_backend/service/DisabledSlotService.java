package com.example.admin_login_backend.service;

import com.example.admin_login_backend.entity.DisabledSlot;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class DisabledSlotService {

    public static final String COLLECTION_NAME = "disabledSlots";

    public List<DisabledSlot> getAllDisabledSlots() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference disabledSlotsCollection = dbFirestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> querySnapshot = disabledSlotsCollection.whereEqualTo("disabled", true).get();

        return querySnapshot.get().getDocuments().stream()
                .map(document -> document.toObject(DisabledSlot.class))
                .collect(Collectors.toList());
    }

    public String updateDisabledSlot(String sala, String date, String timeSlot) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collection = dbFirestore.collection(COLLECTION_NAME);

        // Crear una consulta para encontrar el documento con los campos especificados
        Query query = collection.whereEqualTo("sala", sala)
                .whereEqualTo("date", date)
                .whereEqualTo("timeSlot", timeSlot)
                .whereEqualTo("disabled", true); // Solo actualizar si está actualmente deshabilitado

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        if (!documents.isEmpty()) {
            DocumentReference documentReference = documents.get(0).getReference();
            ApiFuture<WriteResult> writeResult = documentReference.update("disabled", false);
            return "Slot con sala: " + sala + ", fecha: " + date + ", y franja horaria: " + timeSlot + " fue habilitado";
        } else {
            return "No se encontró el slot con sala: " + sala + ", fecha: " + date + ", y franja horaria: " + timeSlot + " deshabilitado";
        }
    }
}