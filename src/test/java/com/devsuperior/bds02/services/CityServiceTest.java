package com.devsuperior.bds02.services;

import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.ControllerNotFoundException;
import com.devsuperior.bds02.services.exceptions.DataBasesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class CityServiceTest {

    @InjectMocks
    private CityService cityService;

    @Mock
    private CityRepository cityRepository;

    private Long existsId;
    private Long nonExistsId;
    private Long dependentId;

    @BeforeEach
    void setUp() throws Exception {
        existsId = 1L;
        nonExistsId = 2L;
        dependentId = 3L;

        Mockito.doNothing().when(cityRepository).deleteById(existsId);
        Mockito.doThrow(ControllerNotFoundException.class).when(cityRepository).deleteById(nonExistsId);
        Mockito.doThrow(DataBasesException.class).when(cityRepository).deleteById(dependentId);
    }

    @Test
    void findAll() {
    }

    @Test
    void insert() {
    }

    @Test
    void deleteShouldThrowDataBasesExceptionWhenIdIsDependent()  {
        Assertions.assertThrows(DataBasesException.class, () -> {
            cityService.delete(dependentId);
        });
        verify(cityRepository, times(1)).deleteById(dependentId);
    }

    @Test
    void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            cityService.delete(nonExistsId);
        });
        verify(cityRepository, times(1)).deleteById(nonExistsId);
    }

    @Test
    void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> {
            cityService.delete(existsId);
        });
        verify(cityRepository, times(1)).deleteById(existsId);
    }
}