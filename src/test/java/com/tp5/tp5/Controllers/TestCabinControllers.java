package com.tp5.tp5.Controllers;

import com.models.Models.Airports;
import com.models.Models.Cabin;
import com.models.payload.request.CabinRequest;
import com.tp5.tp5.Services.CabinService;
import com.tp5.tp5.Services.RouteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestCabinControllers {

    private CabinService cabinService;
    private CabinController cabinController;
    private Cabin cabin;

    private RouteService routeService;

    private CabinRequest cabinRequest;

    private List<Airports> airportsList;

    @Before
    public void config() {

        this.cabinService = mock(CabinService.class);
        this.routeService = mock(RouteService.class);
        this.cabin = new Cabin();

        this.airportsList = new ArrayList<>();

        this.cabinController = new CabinController(this.cabinService, this.routeService);

        this.cabinRequest = new CabinRequest();
        when(this.cabinService.saveCabin("Premium")).thenReturn(this.cabin);
        when(this.cabinService.saveCabin("Plus")).thenReturn(null);

        when(this.cabinService.getAllCabins()).thenReturn(this.airportsList);
    }

    @Test
    public void addCabinGoodTest() {
        this.cabinController.addCabin(this.cabinRequest);
        this.cabinRequest.setName("Premium");
        this.cabinController.addCabin(this.cabinRequest);
        verify(this.routeService, times(1)).addCabin(this.cabinRequest.getRouteId(), this.cabinRequest.getId());
    }

    @Test
    public void addCabinBadTest() {
        this.cabinController.addCabin(this.cabinRequest);
        this.cabinRequest.setName("Plus");
        this.cabinController.addCabin(this.cabinRequest);
        verify(this.routeService, times(0)).addCabin(this.cabinRequest.getRouteId(), this.cabinRequest.getId());
    }

    @Test
    public void deleteCabinTest() {
        this.cabinController.deleteCabin(1);
        verify(this.cabinService, times(1)).deleteCabin(1);
    }

    @Test
    public void updateCabinTest() {
        this.cabinController.updateCabin(this.cabinRequest);
        verify(this.cabinService, times(1)).modifyCabin(this.cabinRequest.getId(), this.cabinRequest.getName());
    }

    @Test
    public void getAllCabinTest(){
        List rtn = this.cabinController.getAllCabins();
        Assert.assertEquals(this.airportsList, rtn);
        verify(this.cabinService,times(1)).getAllCabins();
    }

}
