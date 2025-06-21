package com.upiiz.Examen_3.controllers;

import com.upiiz.Examen_3.models.HabitacionModel;
import com.upiiz.Examen_3.services.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping("/habitaciones")
    public ResponseEntity<List<HabitacionModel>> obtenerTodas() {
        return ResponseEntity.ok(habitacionService.findAllHabitaciones());
    }

    @PostMapping("/habitacion")
    public ResponseEntity<Map<String, Object>> guardarHabitacion(@RequestBody HabitacionModel habitacion) {
        try {
            HabitacionModel guardada = habitacionService.save(habitacion);
            if (guardada.getId() > 0) {
                return ResponseEntity.ok(Map.of(
                        "estado", 1,
                        "mensaje", "Habitación guardada correctamente",
                        "habitacion", guardada
                ));
            } else {
                return ResponseEntity.ok(Map.of(
                        "estado", 0,
                        "mensaje", "No se pudo guardar la habitación"
                ));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(Map.of(
                    "estado", 0,
                    "mensaje", "Error: " + e.getMessage()
            ));
        }
    }

    @PutMapping("/habitacion/{id}")
    public ResponseEntity<Map<String, Object>> actualizarHabitacion(
            @PathVariable int id, @RequestBody HabitacionModel habitacion) {
        habitacion.setId((long) id);
        int filas = habitacionService.update(habitacion);
        if (filas > 0) {
            return ResponseEntity.ok(Map.of(
                    "estado", 1,
                    "mensaje", "Habitación actualizada correctamente"
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "estado", 0,
                    "mensaje", "No se pudo actualizar la habitación"
            ));
        }
    }

    @DeleteMapping("/habitacion/{id}")
    public ResponseEntity<Map<String, Object>> eliminarHabitacion(@PathVariable int id) {
        int filas = habitacionService.delete(id);
        if (filas > 0) {
            return ResponseEntity.ok(Map.of(
                    "estado", 1,
                    "mensaje", "Habitación eliminada correctamente"
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "estado", 0,
                    "mensaje", "No se pudo eliminar la habitación"
            ));
        }
    }
}
