package com.ri.vetclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "species", nullable = false)
    private String species;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

@JsonIgnore
    @OneToMany(mappedBy = "pet", orphanRemoval = true)
    private List<Visit> visits = new ArrayList<>();

}