package com.example.admin_login_backend.controller;

import com.example.admin_login_backend.entity.GestionSalas;
import com.example.admin_login_backend.service.GestionSalasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController // Marca esta clase como un controlador REST
@RequestMapping("/api") // Mapea la ruta base para todas las solicitudes a este controlador
public class GestionSalasController {

    @Autowired // Inyecta automáticamente una instancia de GestionSalasService
    private GestionSalasService gestionSalasService;

    // Maneja las solicitudes GET a "/api/gestionsalas"
    @GetMapping("/gestionsalas")
    public List<GestionSalas> getGestionSalas() throws ExecutionException, InterruptedException {
        return gestionSalasService.getGestionSalas(); // Devuelve todas las salas de gestión
    }

    // Maneja las solicitudes GET a "/api/gestionsalas/{nombre}"
    @GetMapping("/gestionsalas/{nombre}")
    public GestionSalas getSalaByNombre(@PathVariable String nombre) throws ExecutionException, InterruptedException {
        return gestionSalasService.getSalaByNombre(nombre); // Devuelve una sala de gestión por su nombre
    }
}
