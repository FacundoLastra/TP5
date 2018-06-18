package com.tp5.tp5.Controllers;

import com.tp5.tp5.Models.City;
import com.tp5.tp5.Services.CityService;
import com.tp5.tp5.payload.request.CityRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@SpringBootTest
public class TestCityControllers {

    private CityService cityService;
    private CityControllers cityController;
    private City city;
    private CityRequest cityRequest;

    private List<City> cityList;

    @Before
    public void config() {

        this.cityService = mock(CityService.class);
        this.city = new City();

        this.cityList = new ArrayList<>();
        this.cityController = new CityControllers(this.cityService);
        this.cityRequest = new CityRequest();

        //when(this.cityService.saveCity("MDP", "Mar del Plata", "ARS" ));
    }

    @Test
    public void deleteCityTest() {
        this.cityController.deleteCity((long) 1);
        verify(this.cityService, times(1)).deleteCity((long) 1);
    }

    @Test
    public void addCityTest() {
        this.cityController.addCity(this.cityRequest);
        verify(this.cityService, times(1)).saveCity(this.cityRequest.getIata(), this.cityRequest.getName(), this.cityRequest.getStateCode());
    }

    @Test
    public void modifyCityTest() {
        this.cityController.modifyCity(this.cityRequest);
        verify(this.cityService, times(1)).modifyCity(this.cityRequest.getIata(), this.cityRequest.getName(), this.cityRequest.getStateCode());
    }

    @Test
    public void getAllTest(){
        List rtn = this.cityController.getAll();
        Assert.assertEquals(this.cityList, rtn);
        verify(this.cityService,times(1)).getAllCitys();
    }


}

