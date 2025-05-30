package com.ri.vetclinic.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetDTO {
    private String name;
    private String birthDate;
    private String ownerName;
    private String species;
}
