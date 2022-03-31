package com.devsuperior.bds02.controllers;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;
import com.devsuperior.bds02.services.exceptions.ControllerNotFoundException;
import com.devsuperior.bds02.services.exceptions.DataBasesException;
import com.devsuperior.bds02.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityController.class)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @Autowired
    private ObjectMapper objectMapper;

    private CityDTO cityDto;
    private long existingId;
    private long nonExistingId;
    private long depndentId;

    @BeforeEach
    void setUp() throws Exception {
        cityDto = Factory.createCityDTO();
        existingId = 1L;
        nonExistingId = 2L;
        depndentId = 3;

        Mockito.doNothing().when(cityService).delete(existingId);
        Mockito.doThrow(ControllerNotFoundException.class).when(cityService).delete(nonExistingId);
        Mockito.doThrow(DataBasesException.class).when(cityService).delete(depndentId);
    }


        @Test
    void findAll() {
    }

    @Test
    void insert() {
    }

    @Test
    public void deleteShouldReturnNoContentWhenIdExists() throws Exception  {
        ResultActions result = mockMvc.perform(delete("/cities/{id}", existingId));
        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldNotFoundWhenIdDoesNotExists() throws Exception  {
        ResultActions result = mockMvc.perform(delete("/cities/{id}", nonExistingId));
        result.andExpect(status().isNotFound());
    }

    @Test
    public void deleteShouldDataBasesException() throws Exception  {
        ResultActions result = mockMvc.perform(delete("/cities/{id}", depndentId));
        result.andExpect(status().isBadRequest());
    }
}