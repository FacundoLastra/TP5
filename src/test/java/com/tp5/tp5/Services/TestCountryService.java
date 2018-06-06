package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Country;
import com.tp5.tp5.Repository.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest

public class TestCountryService {


    private CountryService countryService;
    private Country country;

    @Before
    public void config(){
      this.country = new Country("Argentina","ARG");
      CountryRepository mockRepository = mock(CountryRepository.class);
      mockRepository.save(this.country);
      when(mockRepository.save(this.country)).thenReturn(this.country);
      when(mockRepository.findAll()).thenReturn(new ArrayList<>());
      when(mockRepository.findById((long)1)).thenReturn(java.util.Optional.ofNullable(this.country));
      this.countryService = new CountryService(mockRepository);

    }
    @Test
    public void saveTest(){
       boolean res=this.countryService.saveCountry(this.country);

        assertEquals(true,res);

    }
    @Test
    public void getAllTest(){
        assertNotNull(this.countryService.getAllCountrys());
    }
    @Test
    public void deleteCountryTest(){
        this.countryService.deleteCountry((long) 1);
    }
    @Test
    public void modifyCountryTest(){
        this.countryService.modifyCountry("ARG","Argentina",(long) 1);
    }

}
