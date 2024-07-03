package com.example.admin_login_backend.controller;

import com.example.admin_login_backend.entity.Reportes;
import com.example.admin_login_backend.service.ReportesService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ReportesController {

    @Autowired
    private ReportesService reportesService;

    @GetMapping("/reportes")
    public List<Reportes> getAllReportes() throws ExecutionException, InterruptedException {
        return reportesService.getAllReportes() ;
    }

    @GetMapping("/reportes/pdf")
    public void generarReportePdf(HttpServletResponse response) throws IOException {
        reportesService.generarReportePdf(response);
}

}