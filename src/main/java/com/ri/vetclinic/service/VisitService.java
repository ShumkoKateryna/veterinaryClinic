package com.ri.vetclinic.service;

import com.ri.vetclinic.DTO.VisitDTO;
import com.ri.vetclinic.model.Pet;
import com.ri.vetclinic.model.Visit;
import com.ri.vetclinic.model.repository.PetRepository;
import com.ri.vetclinic.model.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    PetRepository petRepository;

    public List<Visit> findVisitsByPet(Long petId) {
        return visitRepository.findByPetId(petId);
    }

    public void createVisit(VisitDTO visitDTO) {
        Visit visit = new Visit();
        visit.setDescription(visitDTO.getDescription());
        visit.setPet(petRepository.findById(visitDTO.getPetId()).get());
        visit.setVisitDate(new Timestamp(System.currentTimeMillis()));
        visitRepository.save(visit);
    }


}
