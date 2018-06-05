package com.tp5.tp5.payload.response;

import com.tp5.tp5.Models.Airports;
import lombok.Getter;

@Getter
public class AirportResponse {
    private String iata;
    private String name;
    private  CityResponse city;
    private float latitud;
    private float longitud;
    public AirportResponse(Airports airport){
        this.iata = airport.getIata();
        this.name = airport.getName();
        this.city = new CityResponse(airport.getCity());
        this.latitud = airport.getLatitud();
        this.longitud = airport.getLongitud();
    }
}
