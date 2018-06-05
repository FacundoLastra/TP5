package com.tp5.tp5.payload.response;


import com.tp5.tp5.Models.Route;
import lombok.Getter;


@Getter
public class RouteResponse{
    private AirportResponse airportOrigin;
    private AirportResponse airportDestination;
    private float distance;
    //private List<CabinResponse> cabins;

    public RouteResponse(Route route){
        this.distance = route.getDistance();
        this.airportOrigin = new AirportResponse(route.getOrigin());
        this.airportDestination = new AirportResponse(route.getDestination());
    }
}
