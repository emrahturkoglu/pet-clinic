package com.emrah.petclinic.service.map;

import com.emrah.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class OwnerMapServiceTest {

    private OwnerMapService service;

    private final long OWNER_ID = 1L;

    private final String LASTNAME = "Smith";

    @BeforeEach
    void setUp() {
        service = new OwnerMapService(new PetMapService(), new PetTypeMapService());
        service.save(Owner.builder().id(OWNER_ID).lastName(LASTNAME).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = service.findAll();
        assertNotNull(owners);
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = service.findById(OWNER_ID);
        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void save() {
        long id = 2L;
        Owner owner = service.save(Owner.builder().id(id).build());
        assertNotNull(owner);
        assertEquals(id, owner.getId());
    }

    @Test
    void delete() {
        service.delete(service.findById(OWNER_ID));
        assertEquals(0, service.findAll().size());
    }

    @Test
    void deleteById() {
        service.deleteById(OWNER_ID);
        assertEquals(0, service.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = service.findByLastName(LASTNAME);
        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
    }
}