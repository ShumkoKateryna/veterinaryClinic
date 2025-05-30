package com.ri.vetclinic.model.repository;

import com.ri.vetclinic.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
}