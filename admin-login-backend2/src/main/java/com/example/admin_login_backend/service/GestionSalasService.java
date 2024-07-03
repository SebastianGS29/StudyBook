package com.example.admin_login_backend.service;

import com.example.admin_login_backend.entity.GestionSalas;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service // Marca esta clase como un servicio de Spring
public class GestionSalasService {

    private static final String COLLECTION_NAME = "mantenimiento"; // Nombre de la colección en Firestore

    // Método para obtener todas las salas de gestión
    public List<GestionSalas> getGestionSalas() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore(); // Obtiene una instancia de Firestore
        CollectionReference collectionReference = dbFirestore.collection(COLLECTION_NAME); // Obtiene una referencia a la colección
        ApiFuture<QuerySnapshot> future = collectionReference.get(); // Obtiene un futuro para la consulta de la colección
        List<QueryDocumentSnapshot> documents = future.get().getDocuments(); // Obtiene los documentos de la consulta
        List<GestionSalas> gestionsalaList = new ArrayList<>(); // Inicializa una lista para almacenar los resultados
        for (DocumentSnapshot document : documents) { // Itera sobre los documentos
            if (document.exists()) { // Verifica si el documento existe
                gestionsalaList.add(document.toObject(GestionSalas.class)); // Convierte el documento a un objeto GestionSalas y lo agrega a la lista
            }
        }
        return gestionsalaList; // Devuelve la lista de salas de gestión
    }

    // Método para obtener una sala por su nombre
    public GestionSalas getSalaByNombre(String nombre) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore(); // Obtiene una instancia de Firestore
        CollectionReference collectionReference = dbFirestore.collection(COLLECTION_NAME); // Obtiene una referencia a la colección
        ApiFuture<QuerySnapshot> future = collectionReference.whereEqualTo("nombre", nombre).get(); // Obtiene un futuro para la consulta con filtro
        List<QueryDocumentSnapshot> documents = future.get().getDocuments(); // Obtiene los documentos de la consulta
        if (!documents.isEmpty()) { // Verifica si se encontraron documentos
            return documents.get(0).toObject(GestionSalas.class); // Convierte el primer documento a un objeto GestionSalas y lo devuelve
        }
        return null; // Devuelve nulo si no se encontró ninguna sala con el nombre dado
    }

}
