package com.tp5.tp5.Services;

import com.tp5.tp5.Models.City;
import com.tp5.tp5.Models.Country;
import com.tp5.tp5.Models.State;
import com.tp5.tp5.Repository.CityRepository;
import com.tp5.tp5.Repository.StateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestCityService {

    private City city;

    private CityService cityService;

    private CityRepository mockCityRepository;

    private StateRepository mockStateRepository;

    private State testState;

    @Before
    public void config(){
        this.testState = new State();
        Country country = new Country("Argentina","ARG");
        this.testState = new State(country,"BSA","Buenos Aires");
        this.city = new City("MDQ","Mar del Plata",this.testState);
        this.mockCityRepository = mock(CityRepository.class);
        this.mockStateRepository = mock(StateRepository.class);
        this.mockStateRepository.save(this.testState);
        when(this.mockStateRepository.findByIata("BSA")).thenReturn(java.util.Optional.ofNullable(this.testState));
        when(this.mockCityRepository.findByIata("MDQ")).thenReturn(java.util.Optional.ofNullable(this.city));
        when(this.mockCityRepository.save(new City("MDQ","Mar del Plata",this.testState))).thenReturn(this.city);

        this.cityService = new CityService(this.mockCityRepository,this.mockStateRepository);

    }

    @Test
    public void saveTest(){
        boolean res =this.cityService.saveCity("MDQ","Mar del Plata","BSA");
        verify(this.mockStateRepository,times(1)).findByIata("BSA");
        assertEquals(true,res);

    }
    @Test
    public void deleteTest(){
        this.cityService.deleteCity((long)1);
        verify(this.mockCityRepository,times(1)).deleteById((long)1);
    }
    @Test
    public void getAllTest(){
        assertNotNull(this.cityService.getAllCitys());
    }
    @Test
    public void updateTest(){
        this.cityService.modifyCity("MDQ","Mar del Plata","BSA");
        verify(this.mockStateRepository,times(1)).findByIata("BSA");
        verify(this.mockCityRepository,times(1)).findByIata("MDQ");

    }


}
