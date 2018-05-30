package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Country;
import com.tp5.tp5.Repository.CountryRepository;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCountryService {

    private CountryService countryService;

    @Before
    public void config(){
        Country testCountry = new Country();
        when(testCountry.getId()).thenReturn((long) 1);
        when(testCountry.getCode()).thenReturn("ARG");
        when(testCountry.getName()).thenReturn("Argentina");


    }


}
