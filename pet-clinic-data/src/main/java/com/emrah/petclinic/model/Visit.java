package com.emrah.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Visit {
    private LocalDate localDate;
    private String description;
    private Pet pet;
}
