package com.emrah.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Pet {
    private PetType petType;
    private Owner owner;
    private LocalDateTime birthDate;
}
