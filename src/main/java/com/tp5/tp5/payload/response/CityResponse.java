package com.tp5.tp5.payload.response;

import com.tp5.tp5.Models.City;
import lombok.Getter;
@Getter
public class CityResponse {
    private String iata;
    private String name;
    private StateResponse state;

    public CityResponse(City city){
        this.iata = city.getIata();
        this.name = city.getName();
        this.state = new StateResponse(city.getStateAtribute());
    }
}
