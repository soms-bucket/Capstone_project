package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Repository.AutomobileRepository;
import com.example.demo.Service.impl.AutomobileServiceImpl;
import com.example.demo.entity.Automobile;
import com.example.demo.exception.ResourceNotFoundException;

public class AutomobileServiceTest {

    @Mock
    private AutomobileRepository automobileRepository;

    @InjectMocks
    private AutomobileServiceImpl automobileService;

    private Automobile automobile1;
    private Automobile automobile2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        automobile1 = new Automobile();
        automobile1.setId(1L);
        automobile1.setMake("Make A");
        automobile1.setModel("Model A");
        automobile1.setYear(2020);
        automobile1.setPrice(10000.0);
        automobile1.setManufacturerId(100L);

        automobile2 = new Automobile();
        automobile2.setId(2L);
        automobile2.setMake("Make B");
        automobile2.setModel("Model B");
        automobile2.setYear(2021);
        automobile2.setPrice(20000.0);
        automobile2.setManufacturerId(101L);
    }

    @Test
    void testGetAllAutomobile() {
        List<Automobile> automobiles = Arrays.asList(automobile1, automobile2);
        when(automobileRepository.findAll()).thenReturn(automobiles);

        List<Automobile> result = automobileService.getAllAutomobile();

        assertEquals(2, result.size());
        assertEquals(automobile1, result.get(0));
        assertEquals(automobile2, result.get(1));
    }

    @Test
    void testGetByManufacturerId() throws ResourceNotFoundException {
        List<Automobile> automobiles = Arrays.asList(automobile1);
        when(automobileRepository.findByManufacturerId(100L)).thenReturn(Optional.of(automobiles));

        List<Automobile> result = automobileService.getByManufacturerId(100L);

        assertEquals(1, result.size());
        assertEquals(automobile1, result.get(0));
    }

    @Test
    void testGetAutomobileByYear() {
        List<Automobile> automobiles = Arrays.asList(automobile1);
        when(automobileRepository.findByYear(2020)).thenReturn(automobiles);

        List<Automobile> result = automobileService.getAutomobileByYear(2020);

        assertEquals(1, result.size());
        assertEquals(automobile1, result.get(0));
    }

    @Test
    void testGetAutomobileById() throws ResourceNotFoundException {
        when(automobileRepository.findById(1L)).thenReturn(Optional.of(automobile1));

        Automobile result = automobileService.getAutomobileById(1L);

        assertNotNull(result);
        assertEquals(automobile1, result);
    }

    @Test
    void testCreateAutomobile() {
        when(automobileRepository.save(any(Automobile.class))).thenReturn(automobile1);

        Automobile result = automobileService.createAutomobile(automobile1);

        assertNotNull(result);
        assertEquals(automobile1, result);
    }

    @Test
    void testUpdateAutomobile() throws ResourceNotFoundException {
        Long automobileId = 1L;

        Automobile updatedAutomobile = new Automobile();
        updatedAutomobile.setMake("Updated Make");
        updatedAutomobile.setModel("Updated Model");
        updatedAutomobile.setYear(2022);
        updatedAutomobile.setPrice(15000.0);
      

        when(automobileRepository.findById(automobileId)).thenReturn(Optional.of(automobile1));
        when(automobileRepository.save(any(Automobile.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Automobile result = automobileService.updateAutomobile(automobileId, updatedAutomobile);

        assertNotNull(result);
        assertEquals(automobileId, result.getId());
        assertEquals("Updated Make", result.getMake());
        assertEquals("Updated Model", result.getModel());
        assertEquals(2022, result.getYear());
        assertEquals(15000.0, result.getPrice());
         
    }

    @Test
    void testDeleteAutomobile() throws ResourceNotFoundException {
        Long automobileId = 1L;
        when(automobileRepository.findById(automobileId)).thenReturn(Optional.of(automobile1));
        doNothing().when(automobileRepository).deleteById(automobileId);

        assertDoesNotThrow(() -> automobileService.deleteById(automobileId));
    }
}
