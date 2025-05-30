package com.ri.vetclinic.model.repository;

import com.ri.vetclinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByPetId(Long petId);
}