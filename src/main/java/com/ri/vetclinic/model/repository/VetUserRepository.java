package com.ri.vetclinic.model.repository;

import com.ri.vetclinic.model.VetUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetUserRepository extends JpaRepository<VetUser, Long> {
    VetUser findByUsername(String username);
}