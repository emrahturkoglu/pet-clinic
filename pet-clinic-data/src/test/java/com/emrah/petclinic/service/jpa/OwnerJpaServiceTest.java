package com.emrah.petclinic.service.jpa;

import com.emrah.petclinic.model.Owner;
import com.emrah.petclinic.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerJpaService service;

    private final Long ID = 1L;
    private final String LAST_NAME = "Smith";

    private Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        List<Owner> ownerList = List.of(returnOwner);
        when(ownerRepository.findAll()).thenReturn(ownerList);
        Set<Owner> owners = service.findAll();
        assertEquals(ownerList.size(), owners.size());
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = service.findById(ID);
        assertNotNull(owner);
        assertEquals(ID, owner.getId());
        assertEquals(LAST_NAME, owner.getLastName());
        verify(ownerRepository, times(1)).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = service.findById(ID);
        assertNull(owner);
        verify(ownerRepository, times(1)).findById(anyLong());
    }

    @Test
    void save() {
        when(ownerRepository.save(any(Owner.class))).thenReturn(returnOwner);
        Owner owner = service.save(returnOwner);
        assertNotNull(owner);
        assertEquals(ID, owner.getId());
        assertEquals(LAST_NAME, owner.getLastName());
        verify(ownerRepository, times(1)).save(any(Owner.class));
    }

    @Test
    void delete() {
        service.delete(returnOwner);
        verify(ownerRepository, times(1)).delete(any(Owner.class));
    }

    @Test
    void deleteById() {
        service.deleteById(ID);
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(anyString())).thenReturn(returnOwner);
        Owner owner = service.findByLastName(LAST_NAME);
        assertNotNull(owner);
        assertEquals(ID, owner.getId());
        assertEquals(LAST_NAME, owner.getLastName());
        verify(ownerRepository, times(1)).findByLastName(anyString());
    }
}