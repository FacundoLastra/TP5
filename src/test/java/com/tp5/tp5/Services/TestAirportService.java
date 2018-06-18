package com.tp5.tp5.Services;

import com.models.Models.Airports;
import com.models.Models.City;
import com.tp5.tp5.Repository.AirportsRepository;
import com.tp5.tp5.Repository.CityRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest //marco que es un test
public class TestAirportService {

    private AirportsService testAirportService;
    private AirportsRepository testAirportRespository;
    private CityRepository testCityRepository;
    private Airports testAir;


    @Before
    public void config(){
        this.testAir = new Airports();
        this.testCityRepository = mock(CityRepository.class);
        this.testAirportRespository = mock(AirportsRepository.class);
        when(this.testCityRepository.findByIata("MDQ")).thenReturn(java.util.Optional.of(new City()));
        when(this.testAirportRespository.findByIata("EZE")).thenReturn(java.util.Optional.of(new Airports()));
        when(this.testAirportRespository.save(this.testAir)).thenReturn(this.testAir);
        this.testAirportService = new AirportsService(this.testAirportRespository,this.testCityRepository);
    }

    @Test
    public void saveTest(){
        this.testAirportService.saveAirport("EZE","Ezeiza","MDQ",54,54);
        verify(this.testCityRepository,times(1)).findByIata("MDQ");
        verify(this.testAirportRespository,times(1)).save(new Airports("EZE","Ezeiza",new City(),54,54));

    }
    @Test
    public void deleteTest(){
        this.testAirportService.deleteAirport((long)1);
        verify(this.testAirportRespository,times(1)).deleteById((long)1);
    }
    @Test
    public void findAllTest(){
        assertNotNull(this.testAirportService.getAllAirports());
    }
    @Test
    public void updateTest(){
        this.testAirportService.modifyAirport("EZE","Ezeiza","MDQ",54,54);
        verify(this.testAirportRespository,times(1)).findByIata("EZE");
        verify(this.testCityRepository,times(1)).findByIata("MDQ");
    }

}
