package com.ri.vetclinic.controller;

import com.ri.vetclinic.model.Veterinarian;
import com.ri.vetclinic.service.VeterinarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vets")
public class VeterinarianController {

    @Autowired
    private VeterinarianService veterinarianService;

    // GET /vets — список ветеринарів
    @GetMapping
    public ResponseEntity<List<Veterinarian>> getAllVeterinarians() {
        List<Veterinarian> veterinarians = veterinarianService.findAll();
        return ResponseEntity.ok(veterinarians);
    }

    // GET /vets/{id} — інформація про ветеринара
    @GetMapping("/{id}")
    public ResponseEntity<Veterinarian> getVeterinarianById(@PathVariable Long id) {
        try {
            Veterinarian veterinarian = veterinarianService.findById(id);
            return ResponseEntity.ok(veterinarian);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}