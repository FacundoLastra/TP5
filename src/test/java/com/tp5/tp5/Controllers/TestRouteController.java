package com.tp5.tp5.Controllers;

import com.models.Models.Route;
import com.models.payload.request.RouteRequest;
import com.tp5.tp5.Services.RouteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestRouteController {

    private RouteController routeController;
    private RouteService routeService;
    private Route route;
    private RouteRequest routeRequest;

    private List<Route> routeList;

    @Before
    public void config(){

        this.routeService = mock(RouteService.class);
        this.routeController = new RouteController(this.routeService);
        this.route = new Route();
        this.routeRequest = new RouteRequest();

        this.routeList = new ArrayList<>();
    }

    @Test
    public void addRouteTest() {
        this.routeController.addRoute(this.routeRequest);
        verify(this.routeService, times(1)).saveRoute(this.routeRequest.getIataAirportOrigin(),
                this.routeRequest.getIataAirportDestination(), this.routeRequest.getDistance());
    }

    @Test
    public void deleteRouteTest() {
        this.routeController.deleteRoute(1);
        verify(this.routeService, times(1)).deleteRoute(1);
    }

    @Test
    public void updateRouteTest() {
        this.routeController.updateRoute(this.routeRequest);
        verify(this.routeService, times(1)).updateRoute(this.routeRequest.getId(),
                this.routeRequest.getIataAirportOrigin(),
                this.routeRequest.getIataAirportDestination(), this.routeRequest.getDistance());
    }

    @Test
    public void getAllRoutesTest() {
        List rtn = this.routeController.getAllRoutes();
        verify(this.routeService, times(1)).getAllRoutes();
        Assert.assertEquals(this.routeList, rtn);
    }

    @Test
    public void getAirportsWhitOriginTest() {
        List rtn = this.routeController.getAirportsWhitOrigin();
        verify(this.routeService, times(1)).getAirportsWhitOriginRoutes();
        Assert.assertEquals(this.routeList, rtn);
    }

    @Test
    public void getAiportDestinationTest() {
        List rtn = this.routeController.getAiportDestination(this.routeRequest.getIataAirportDestination());
        verify(this.routeService, times(1)).getDestinationAirportsForOrigin(this.routeRequest.getIataAirportDestination());
        Assert.assertEquals(this.routeList, rtn);
    }
}
