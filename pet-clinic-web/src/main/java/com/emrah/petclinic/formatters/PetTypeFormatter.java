package com.emrah.petclinic.formatters;

import com.emrah.petclinic.model.PetType;
import com.emrah.petclinic.service.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();

        for (PetType type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }

        throw new ParseException("type not found: " + text, 0);
    }
}

