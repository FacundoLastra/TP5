package com.tp5.tp5.Services;

import com.models.Models.*;
import com.tp5.tp5.Repository.AirportsRepository;
import com.tp5.tp5.Repository.CabinRepository;
import com.tp5.tp5.Repository.RouteRepository;
import com.tp5.tp5.Services.RouteService;
import com.tp5.tp5.Repository.PriceRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestPriceService {

    private PriceService priceService;
    private PriceRepository priceRepository;
    private Price price;

    private RouteService routeService;
    private RouteService routeServiceMock;
    private RouteRepository routeRepository;
    private Route route;
    private Optional<Route> routeOptional;

    private CabinService cabinService;
    private CabinRepository cabinRepository;
    private Cabin cabin;

    private AirportsRepository airportsRepository;


    @Before
    public void config() {

        this.priceRepository = mock(PriceRepository.class);
        this.routeRepository = mock(RouteRepository.class);
        this.cabinRepository = mock(CabinRepository.class);
        this.airportsRepository = mock(AirportsRepository.class);

        this.routeService = mock(RouteService.class);
        //this.routeService = new RouteService(this.routeRepository, this.airportsRepository, this.cabinRepository );

        this.priceService = new PriceService(this.priceRepository, this.cabinService, this.routeService);


        Set<Cabin_Route> cabinRouteOptionalTest = new HashSet<>();
        Cabin_Route testCabin_Route = new Cabin_Route((long)2,new Cabin((long)2,"turista",null),this.route,new HashSet<>());
        this.price = new Price((float)2,"01/01/2018", "02/01/2018",null);
        testCabin_Route.getPriceList().add(this.price);
        cabinRouteOptionalTest.add(testCabin_Route);
        this.route = new Route((long)1,(float)10,new Airports(),new Airports(),cabinRouteOptionalTest);
        this.cabin = new Cabin();
        this.routeOptional = Optional.of(this.route);

        Optional<Price> empatyPrice = Optional.empty();
        Optional<Route> empatyRoute = Optional.empty();


        when(this.routeService.getById((long) 1)).thenReturn(empatyRoute);

        when(this.routeService.getById((long) 2)).thenReturn(this.routeOptional);

        when(this.priceRepository.findById((long)2)).thenReturn(Optional.of(this.price));

        when(this.priceRepository.findById((long)3)).thenReturn(empatyPrice);

        when(this.routeService.getRouteByAirportIataOriginAndDestination("EZE","EZE")).thenReturn(this.route);



    }

    @Test
    public void saveBadPriceTest() {
        this.priceService.savePrice(1, 1, 1, "01/01/2018", "02/01/2018");
        verify(this.priceRepository, times(0)).save(this.price);
    }

    @Test
    public void saveGoodPriceTest() {
        this.priceService.savePrice( 2,2, 2,"01/01/2018", "02/01/2018");
        Cabin_Route testCabin_Route = new Cabin_Route((long)2,new Cabin((long)2,"turista",null),this.route,null);
        Price testPrice = this.price;
        testPrice.setCabin_Route(testCabin_Route);
        //verify(this.priceRepository,times(1)).save(testPrice);

    }

    @Test
    public void deletePriceTest(){
        this.priceService.deletePrice((long)1);
        verify(this.priceRepository,times(1)).deleteById((long)1);
    }

    @Test
    public void getAllPricesTest(){
        this.priceRepository.save(this.price);
        List res = this.priceService.getAllPrices();
        assertNotNull(res);
    }

    @Test
    public void updateGoodPriceTest(){
       Price price= this.priceService.updatePrice(2,35,"10/05/2018","20/08/2019");
        assertNotNull(price);
    }

    @Test
    public void updateBadPriceTest(){
        Price price = this.priceService.updatePrice(3,35,"10/05/2018","20/09/2019");
        assertNull(price);
    }
    @Test
    public void getPriceWhitRouteAndDateTest(){
        LocalDate date = LocalDate.of(2018,Month.DECEMBER,25);
        List list = this.priceService.getPriceWhitRouteAndDate("EZE", "EZE", date);
        assertNotNull(list);

    }
}

