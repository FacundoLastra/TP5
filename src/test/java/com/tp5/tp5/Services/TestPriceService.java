package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Cabin;
import com.tp5.tp5.Models.Cabin_Route;
import com.tp5.tp5.Models.Price;
import com.tp5.tp5.Models.Route;
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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestPriceService {

    private PriceService priceService;
    private PriceRepository priceRepository;
    private Price price;

    private RouteService routeService;
    private RouteRepository routeRepository;
    private Route route;
    private Optional<Route> routeOptional;
    private Optional<Cabin_Route> cabinRouteOptional;
    private Set<Optional<Cabin_Route>> asd = new HashSet<>();

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

        this.routeService = new RouteService(this.routeRepository, this.airportsRepository, this.cabinRepository );

        this.priceService = new PriceService(this.priceRepository, this.cabinService, this.routeService);

        this.price = new Price();
        this.route = mock(Route.class);
        this.cabin = new Cabin();

        Optional<Price> empatyPrice = Optional.empty();
        Optional<Route> empatyRoute = Optional.empty();

        when(this.routeService.getById((long) 1)).thenReturn(empatyRoute);

        when(this.routeService.getById((long) 2)).thenReturn(this.routeOptional);
        when(this.routeOptional.get().getCabinRouteSet()).thenReturn(this.asd);

    }

    @Test
    public void saveBadPriceTest() {
        this.priceService.savePrice(1, 1, 1, "01/01/2018", "02/01/2018");
        verify(this.route, times(0)).getCabinRouteSet();
        verify(this.priceRepository, times(0)).save(this.price);
    }

    @Test
    public void saveGoodPriceTest() {
        this.priceService.savePrice( 2,2, 2,"01/01/2018", "02/01/2018");

    }


}

/*
        Cabin res = this.cabinService.saveCabin("Premium");
        verify(this.cabinRepository, times(1)).findByName("Premium");
        verify(this.cabinRepository, times(0)).save(this.cabinPremium);

        Assert.assertEquals(res, null);
 */
