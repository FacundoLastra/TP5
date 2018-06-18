package com.tp5.tp5.Services;

import com.models.Models.Country;
import com.tp5.tp5.Repository.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest

public class TestCountryService {


    private CountryService countryService;
    private Country country;
    private CountryRepository mockCountryRepository;

    @Before
    public void config(){
      this.country = new Country("Argentina","ARG");
      this.mockCountryRepository = mock(CountryRepository.class);
      this.mockCountryRepository.save(this.country);
      when(this.mockCountryRepository.save(this.country)).thenReturn(this.country);
      when(this.mockCountryRepository.findAll()).thenReturn(new ArrayList<>());
      when(this.mockCountryRepository.findById((long)1)).thenReturn(java.util.Optional.ofNullable(this.country));
      this.countryService = new CountryService(this.mockCountryRepository);

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
        verify(this.mockCountryRepository,times(1)).deleteById((long)1);
    }
    @Test
    public void modifyCountryTest(){
        this.countryService.modifyCountry("ARG","Argentina",(long) 1);
        verify(this.mockCountryRepository,times(1)).findById((long)1);
    }

}
