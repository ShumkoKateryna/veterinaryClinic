package com.ri.vetclinic.controller;

import com.ri.vetclinic.DTO.PetDTO;
import com.ri.vetclinic.model.Pet;
import com.ri.vetclinic.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    // GET /pets — список усіх тварин
    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.findAll();
        return ResponseEntity.ok(pets);
    }

    // GET /pets/{id} — інформація про тварину
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        try {
            Pet pet = petService.findById(id);
            return ResponseEntity.ok(pet);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /pets — додати тварину
    @PostMapping
    public ResponseEntity<String> createPet(@RequestBody PetDTO petDTO) {
        try {
            petService.save(petDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Pet created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error creating pet: " + e.getMessage());
        }
    }
}