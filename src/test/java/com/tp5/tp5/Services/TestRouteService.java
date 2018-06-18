package com.tp5.tp5.Services;

import com.models.Models.*;
import com.tp5.tp5.Repository.AirportsRepository;
import com.tp5.tp5.Repository.CabinRepository;
import com.tp5.tp5.Repository.RouteRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestRouteService {

    private RouteService routeService;
    private RouteRepository routeRepository;
    private AirportsRepository airportsRepositorymock;
    private CabinRepository cabinRepository;
    private Airports airportTest;
    private Route routeTest;
    private Cabin cabinTest;

    @Before
    public void config(){
        this.cabinRepository = mock(CabinRepository.class);
        this.airportsRepositorymock = mock(AirportsRepository.class);
        this.routeRepository = mock(RouteRepository.class);

        this.airportTest = new Airports("EZE","EZEIZA",new City(),(float)52.5,(float)38.5);
        this.routeTest = new Route((float)55,this.airportTest,this.airportTest);
        this.cabinTest = new Cabin("turista");
        when(this.airportsRepositorymock.findByIata("EZE")).thenReturn(Optional.of(this.airportTest));
        when(this.airportsRepositorymock.findByIata("FKA")).thenReturn(Optional.of(this.airportTest));
        when(this.airportsRepositorymock.findByIata("Ez")).thenReturn(Optional.empty());

        when(this.routeRepository.findById((long)1)).thenReturn(Optional.of(this.routeTest));
        when(this.cabinRepository.findById((long)1)).thenReturn(Optional.of(this.cabinTest));

        when(this.routeRepository.findByOriginAndDestination(this.airportTest,this.airportTest)).thenReturn(Optional.of(this.routeTest));


        this.routeService = new RouteService(this.routeRepository,this.airportsRepositorymock,this.cabinRepository);

    }

    @Test
    public void saveRouteTestGood(){
        boolean res = this.routeService.saveRoute("EZE","FKA",(float)55);
        verify(this.routeRepository,times(1)).save(this.routeTest);
        assertTrue(res);
    }
    @Test
    public void saveRouteTestBad(){
        boolean res = this.routeService.saveRoute("Ez","FKA",(float)55);
        verify(this.routeRepository,times(0)).save(this.routeTest);
        assertFalse(res);
    }
    @Test
    public void deleteTest(){
        this.routeService.deleteRoute((long)1);
        verify(this.routeRepository,times(1)).deleteById((long)1);
    }
    @Test
    public void addCabinTestGood(){
        this.routeService.addCabin((long)1,(long)1);
        Route route = this.routeTest;
        route.getCabinRouteSet().add(new Cabin_Route());
        verify(this.routeRepository,times(1)).save(route);
    }
    @Test
    public void updateRouteTest(){
        this.routeService.updateRoute(1,"EZE","EZE",55);
        verify(this.routeRepository,times(1)).save(this.routeTest);
    }
    @Test
    public void getAllRoutesTest(){
        this.routeRepository.save(this.routeTest);
        List list = this.routeService.getAllRoutes();
        assertNotNull(list);
    }
    @Test
    public void getAirportsWhitOriginRoutesTest(){
        this.routeRepository.save(this.routeTest);
        List list = this.routeService.getAirportsWhitOriginRoutes();
        assertNotNull(list);
    }
    @Test
    public void getDestinationAirportsForOriginTest(){
        this.routeRepository.save(this.routeTest);
        List list = this.routeService.getDestinationAirportsForOrigin("EZE");
        assertNotNull(list);
    }
    @Test
    public void getByIdTest(){
        Optional<Route> optRout = this.routeService.getById(1);
        assertTrue(optRout.isPresent());
    }
    @Test
    public void getRouteByAirportIataOriginAndDestinationTestGood(){
        Route route = this.routeService.getRouteByAirportIataOriginAndDestination("EZE","FKA");
        assertNotNull(route);
    }
    @Test
    public void getRouteByAirportIataOriginAndDestinationTestBad(){
        Route route = this.routeService.getRouteByAirportIataOriginAndDestination("Ez","FKA");
        assertNull(route);
    }

}
