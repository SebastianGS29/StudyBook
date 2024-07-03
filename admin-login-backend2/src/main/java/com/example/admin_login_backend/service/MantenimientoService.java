package com.example.admin_login_backend.service;

import com.example.admin_login_backend.entity.Mantenimiento;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class MantenimientoService {
    private static final String COLLECTION_NAME = "mantenimiento";

    // Método para obtener un registro de mantenimiento por su nombre
    public Mantenimiento obtenerMantenimiento(String nombre) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(nombre); // Obtiene una referencia al documento

        // Obtiene los datos actuales del documento
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        // Verifica si el documento existe
        if (document.exists()) {
            // Convierte los datos del documento a un objeto Mantenimiento
            Mantenimiento mantenimiento = document.toObject(Mantenimiento.class);
            return mantenimiento;
        } else {
            // Si el documento no existe, devuelve null
            System.out.println("El registro de mantenimiento '" + nombre + "' no existe.");
            return null;
        }
    }

    // Método para actualizar un registro de mantenimiento
    public void actualizarMantenimiento(String nombre, Mantenimiento nuevoMantenimiento) throws ExecutionException, InterruptedException {
        Mantenimiento mantenimientoExistente = obtenerMantenimiento(nombre);

        // Verifica si el registro de mantenimiento existe
        if (mantenimientoExistente != null) {
            // Actualiza el registro de mantenimiento con los nuevos datos
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(nombre); // Obtiene una referencia al documento que se va a actualizar
            documentReference.set(nuevoMantenimiento); // Actualiza el documento con los nuevos datos
            System.out.println("Registro de mantenimiento actualizado correctamente: " + nombre);
        } else {
            System.out.println("No se pudo actualizar el registro de mantenimiento '" + nombre + "' porque no existe.");
        }
    }
}