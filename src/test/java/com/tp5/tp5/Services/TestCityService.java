package com.tp5.tp5.Services;

import com.tp5.tp5.Models.City;
import com.tp5.tp5.Models.State;
import com.tp5.tp5.Repository.CityRepository;
import com.tp5.tp5.Repository.StateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestCityService {

    private City city;

    private CityService cityService;

    @Before
    public void config(){
        State state = new State();
        CityRepository cityRepositoryMock = mock(CityRepository.class);
        StateRepository stateRepositoryMock = mock(StateRepository.class);
        when(stateRepositoryMock.findByIata("BSA")).thenReturn(java.util.Optional.ofNullable(state));

    }

    @Test
    public void saveTest(){

    }


}
