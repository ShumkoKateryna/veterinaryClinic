package com.ri.vetclinic.service;

import com.ri.vetclinic.DTO.PetDTO;
import com.ri.vetclinic.model.Pet;
import com.ri.vetclinic.model.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet findById(Long id) {
        return petRepository.findById(id).get();
    }

    public void save(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setName(petDTO.getName());
        pet.setSpecies(petDTO.getSpecies());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setOwnerName(petDTO.getOwnerName());
        petRepository.save(pet);
    }

}
