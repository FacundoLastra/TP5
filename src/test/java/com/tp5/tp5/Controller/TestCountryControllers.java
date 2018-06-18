package com.tp5.tp5.Controller;

import com.tp5.tp5.Controllers.CountryControllers;
import com.tp5.tp5.Models.Country;
import com.tp5.tp5.Services.CountryService;
import com.tp5.tp5.payload.request.CountryRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestCountryControllers {

    private CountryService countryService;
    private CountryControllers countryControllers;
    private Country country;
    private CountryRequest countryRequest;

    private List<Country> countryList;

    @Before
    public void config() {

        this.countryService = mock(CountryService.class);
        this.countryControllers = new CountryControllers(this.countryService);
        this.country = new Country();
        this.countryRequest = new CountryRequest();

        this.countryList = new ArrayList<>();
    }

    @Test
    public void deleteCountryTest() {
        this.countryControllers.deleteCountry((long) 1);
        verify(this.countryService, times(1)).deleteCountry((long) 1);
    }

    @Test
    public void addCountryTest(){
        this.countryControllers.addCountry(this.countryRequest);
        verify(this.countryService, times(1)).saveCountry(this.country);
    }

    @Test
    public void modifyCountryTest(){
        this.countryControllers.modifyCountry(this.countryRequest);
        verify(this.countryService, times(1)).modifyCountry(this.countryRequest.getCode(), this.countryRequest.getName(), this.countryRequest.getId());
    }

    @Test
    public void getAllTest() {
        List rtn = this.countryControllers.getAll();
        Assert.assertEquals(this.countryList, rtn);
        verify(this.countryService, times(1)).getAllCountrys();
    }
}
