package com.tp5.tp5.Controllers;

import com.models.Models.Airports;
import com.models.payload.request.AirportRequest;
import com.tp5.tp5.Services.AirportsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestAirportsControllers {

    private AirportsService airportsService;
    private AirportsControllers airportsControllers;

    private Airports airports;
    private AirportRequest airportRequest;

    private List<Airports> airportsList;

    private Exception exception;

    @Before
    public void config() {

        this.airportsService = mock(AirportsService.class);
        this.airportsControllers = new AirportsControllers(this.airportsService);

        this.airportRequest = new AirportRequest();
        this.airports = new Airports();
        this.airportsList = new ArrayList<>();

        this.exception = new NullPointerException();

        when(this.airportsService.getAllAirports()).thenReturn(this.airportsList);
    }

    @Test
    public void deleteAirportTest() {
        this.airportsControllers.deleteAirport((long) 1);
        verify(this.airportsService, times(1)).deleteAirport((long) 1);
    }

    @Test
    public void addAirportTest() {
        this.airportsControllers.addAirport(this.airportRequest);
        verify(this.airportsService, times(1)).saveAirport(this.airportRequest.getIata(), this.airportRequest.getName(), this.airportRequest.getCityCode(), this.airportRequest.getLongitud(), this.airportRequest.getLatitud());
    }

    @Test
    public void modifyAirportsTest() {
        this.airportsControllers.modifyAirport(this.airportRequest);
        verify(this.airportsService, times(1)).modifyAirport(this.airportRequest.getIata(), this.airportRequest.getName(), this.airportRequest.getCityCode(), this.airportRequest.getLongitud(), this.airportRequest.getLatitud());
    }

    @Test
    public void getAllTest() {
        List rtn = this.airportsControllers.getAll();
        Assert.assertEquals(this.airportsList, rtn);
        verify(this.airportsService,times(1)).getAllAirports();
    }


}
