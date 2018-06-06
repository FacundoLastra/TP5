package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Country;
import com.tp5.tp5.Models.State;
import com.tp5.tp5.Repository.CountryRepository;
import com.tp5.tp5.Repository.StateRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestStateService {

    private StateServices stateServices;
    private State state;
    private Country country;
    private StateRepository mockStateRepository;
    @Before
    public void config(){
        this.country = new Country("Argentina","ARG");
        this.state = new State(country,"BSA","Buenos Aires");

        CountryRepository mockCountryRepository = mock(CountryRepository.class);
        when(mockCountryRepository.findByCode("ARG")).thenReturn(java.util.Optional.ofNullable(this.country));

        this.mockStateRepository = mock(StateRepository.class);
        when(mockStateRepository.save(new State(this.country,"BSA","Buenos Aires"))).thenReturn(this.state);
        mockStateRepository.save(this.state);
        when(mockStateRepository.findAll()).thenReturn(new ArrayList<>());
        when(mockStateRepository.findByIata("BSA")).thenReturn(java.util.Optional.ofNullable(this.state));
        this.stateServices = new StateServices(mockStateRepository,mockCountryRepository);
    }
    @Test
    public void saveTest(){
        this.stateServices.saveState("BSA","Buenos Aires","ARG");
        verify(this.mockStateRepository,times(1)).save(this.state);
    }
    @Test
    public void deleteTest(){
        this.stateServices.deleteState((long)1);
    }
    @Test
    public void modifyStateTest(){
        this.stateServices.modifyState("BSA","Buenos Aires","ARG");
    }
    @Test
    public void getAllStateTest(){
        assertNotNull(this.stateServices.getAllStates());
    }

}
