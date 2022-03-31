package com.devsuperior.bds02.tests;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;

import java.time.LocalDate;

public class Factory {
    
    public static Event createEvent() {
        City city = new City(1L, "São Paulo");
        Event event = new Event(1L, "Feira do Software",  LocalDate.of(2021, 5, 16), "https://feiradosoftware.com", city);
        return event;
    }
    
    public static City createCity() {
        City city = new City(1L, "São Paulo");
        Event event = new Event(1L, "Feira do Software",  LocalDate.of(2021, 5, 16), "https://feiradosoftware.com", city);
        return city;
    }

    public static CityDTO createCityDTO() {
        City city = createCity();
        return new CityDTO(city);
    }
}
