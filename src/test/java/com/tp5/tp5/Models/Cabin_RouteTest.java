package com.tp5.tp5.Models;

import com.tp5.tp5.payload.response.PriceResponse;
import com.tp5.tp5.payload.response.RouteResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class Cabin_RouteTest {
    private Country countryTest;
    private State stateTest;
    private City cityTest;
    private Airports airportTest;
    private Cabin cabinTest;
    private Route routeTest;
    private Price priceTest;
    private Cabin_Route cabin_routeTest;

    @Before
    public void config() {
        this.countryTest = new Country("Argentina", "AR");
        this.stateTest = new State(this.countryTest,"BSA","Buenos Aires");
        this.cityTest = new City("Mar del Plata", "MDQ", this.stateTest);
        this.cabinTest = new Cabin( "Turista");
        this.airportTest = new Airports("EZE", "EZEIZA", this.cityTest, (float) 30.2, (float) 15.5);
        this.routeTest = new Route((float) 23.24,this.airportTest, this.airportTest);
        this.cabin_routeTest = new Cabin_Route( this.cabinTest, this.routeTest);
        this.priceTest = new Price((float)1400, "10/05/2008", "10/05/2008", this.cabin_routeTest);
    }

    @Test
    public void classesWhitData()
    {

        assertNotNull(this.priceTest);
        assertEquals(this.cabin_routeTest,this.priceTest.getCabin_Route());
        assertEquals("EZEIZA",this.routeTest.getOrigin().getName());
        assertEquals(this.countryTest,this.airportTest.getCity().getStateAtribute().getNation());

    }
    @Test
    public void generateDtoRequest(){
        PriceResponse res = new PriceResponse(this.priceTest);
    }
}
