package com.example.admin_login_backend.controller;

import com.example.admin_login_backend.entity.Mantenimiento;
import com.example.admin_login_backend.service.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class MantenimientoController {
    @Autowired
    private MantenimientoService mantenimientoService;

    // Método para obtener un registro de mantenimiento por su nombre
    @GetMapping("/mantenimiento/{nombre}")
    public ResponseEntity<Mantenimiento> obtenerMantenimiento(@PathVariable String nombre) {
        try {
            Mantenimiento mantenimiento = mantenimientoService.obtenerMantenimiento(nombre);
            if (mantenimiento != null) {
                return ResponseEntity.ok().body(mantenimiento);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ExecutionException | InterruptedException e) {
            // Manejo de excepciones
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // Método para actualizar un registro de mantenimiento
    @PutMapping("/mantenimiento/{nombre}")
    public void actualizarMantenimiento(@PathVariable String nombre, @RequestBody Mantenimiento nuevoMantenimiento) {
        try {
            // Verifica si el registro de mantenimiento existe antes de actualizarlo
            Mantenimiento mantenimientoExistente = mantenimientoService.obtenerMantenimiento(nombre);
            if (mantenimientoExistente != null) {
                // Actualiza el registro de mantenimiento
                mantenimientoService.actualizarMantenimiento(nombre, nuevoMantenimiento);
                System.out.println("Registro de mantenimiento '" + nombre + "' actualizado correctamente.");
            } else {
                System.out.println("No se puede actualizar el registro de mantenimiento '" + nombre + "' porque no existe.");
            }
        } catch (ExecutionException | InterruptedException e) {
            // Manejo de excepciones
            e.printStackTrace();
            // Podrías lanzar una excepción personalizada o manejarla de otra manera según tus necesidades
        }
    }
}