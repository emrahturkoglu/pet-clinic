package com.emrah.petclinic.services.map;

import com.emrah.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner = Owner.builder().build();
        Owner savedOwner = ownerMapService.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
        assertEquals(lastName, owner.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerMapService.findByLastName("foo");
        assertNull(owner);
    }
}