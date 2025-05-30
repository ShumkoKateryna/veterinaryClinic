package com.ri.vetclinic.service;

import com.ri.vetclinic.model.Veterinarian;
import com.ri.vetclinic.model.repository.VeterinarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarianService {

    @Autowired
    private VeterinarianRepository veterinarianRepository;

    public List<Veterinarian> findAll() {
        return veterinarianRepository.findAll();
    }

    public Veterinarian findById(Long id) {
        return veterinarianRepository.findById(id).get();
    }

}
