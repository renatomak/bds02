package com.devsuperior.bds02.repositories;

import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    private long existingId;
    private long nonexistingId;
    private long countTotalCategories;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonexistingId = 1000L;
        countTotalCategories = 11;
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
        City city = Factory.createCity();
        city.setId(null);
        city = cityRepository.save(city);

        Assertions.assertNotNull(city.getId());
        Assertions.assertEquals(countTotalCategories + 1, city.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        // Act
        cityRepository.deleteById(existingId);
        Optional<City> result = cityRepository.findById(existingId);

        // Assert
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            cityRepository.deleteById(nonexistingId);
        });
    }

//    @Test
//    public void deleteShouldThrowDataIntegrityViolationExceptionWhenIdDoesNotExist() {
//        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
//            cityRepository.deleteById(1L);
//        });
//    }

}