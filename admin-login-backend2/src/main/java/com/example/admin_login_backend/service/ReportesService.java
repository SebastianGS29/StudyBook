package com.example.admin_login_backend.service;

import com.example.admin_login_backend.entity.Reportes;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ReportesService {

    private static final String COLLECTION_NAME = "reservations";

    public List<Reportes> getAllReportes() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = dbFirestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Reportes> reportesList = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            if (document.exists()) {
                reportesList.add(document.toObject(Reportes.class));
            }
        }
        return reportesList;
    }

    public void generarReportePdf(HttpServletResponse response) throws IOException {
        // Configurar la respuesta HTTP
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");

        // Crear el documento PDF
        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Agregar contenido al PDF

        List<Reportes> reportesList = null;
        try {
            reportesList = getAllReportes();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        String imageUrl = "src/main/java/com/example/admin_login_backend/images/logo.jpg"; // Reemplaza con la URL de tu imagen
        ImageData data = ImageDataFactory.create(imageUrl);
        Image image = new Image(data);
        image.setFixedPosition(370, 650); // Ajusta la posición según sea necesario
        image.scaleToFit(200, 200); // Ajusta el tamaño según sea necesario
        document.add(image);

        for (Reportes reporte : reportesList) {
            document.add(new Paragraph("Fecha: " + reporte.getDate()));
            document.add(new Paragraph("Horario: " + reporte.getHorario()));
            document.add(new Paragraph("Sala: " + reporte.getSala()));
            document.add(new Paragraph("Timestamp: " + reporte.getTimestamp()));
            document.add(new Paragraph("Estudiantes: " + reporte.getStudents()));
            document.add(new Paragraph("\n"));
        }

        // Cerrar el documento
        document.close();
}
}