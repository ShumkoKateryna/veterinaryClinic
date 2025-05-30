package com.ri.vetclinic.controller;
import com.ri.vetclinic.DTO.VisitDTO;
import com.ri.vetclinic.model.Visit;
import com.ri.vetclinic.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitController {

    @Autowired
    private VisitService visitService;

    // POST /visits — запис на прийом
    @PostMapping
    public ResponseEntity<String> createVisit(@RequestBody VisitDTO visitDTO) {
        try {
            visitService.createVisit(visitDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Visit created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error creating visit: " + e.getMessage());
        }
    }

    // GET /visits/by-pet/{petId} — історія візитів тварини
    @GetMapping("/by-pet/{petId}")
    public ResponseEntity<List<Visit>> getVisitsByPet(@PathVariable Long petId) {
        try {
            List<Visit> visits = visitService.findVisitsByPet(petId);
            return ResponseEntity.ok(visits);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}