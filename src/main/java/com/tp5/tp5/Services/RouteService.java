package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Airports;
import com.tp5.tp5.Models.Cabin;
import com.tp5.tp5.Models.Cabin_Route;
import com.tp5.tp5.Models.Route;
import com.tp5.tp5.Repository.AirportsRepository;
import com.tp5.tp5.Repository.CabinRepository;
import com.tp5.tp5.Repository.RouteRepository;
import com.tp5.tp5.payload.response.AirportResponse;
import com.tp5.tp5.payload.response.RouteResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor @NoArgsConstructor
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private AirportsRepository airportsRepository;
    @Autowired
    private CabinRepository cabinRepository;

    public boolean saveRoute(String aitaAirportOrigin, String aitaAirportDestination,float distance){
        boolean res=false;
        Optional<Airports> airOrigin=this.airportsRepository.findByIata(aitaAirportOrigin);
        Optional<Airports> airDestination=this.airportsRepository.findByIata(aitaAirportDestination);

        if(airOrigin.isPresent() && airDestination.isPresent()){
            this.routeRepository.save(new Route(distance,airOrigin.get(),airDestination.get()));
            res = true;
        }

        return res;
    }
    public void deleteRoute(long id){

        this.routeRepository.deleteById(id);

    }

    public void addCabin(long idRoute, long idCabin){
        Optional<Route> route = this.routeRepository.findById(idRoute);
        Optional<Cabin> cabin = this.cabinRepository.findById(idCabin);

        if (route.isPresent() && cabin.isPresent()){
            Cabin_Route cabin_route= new Cabin_Route(cabin.get(),route.get());

            route.get().getCabinRouteSet().add(cabin_route);

            this.routeRepository.save(route.get());
        }
    }

    public void updateRoute(long idRoute,String aitaAirportOrigin, String aitaAirportDestination,float distance){

        Route route = this.routeRepository.findById(idRoute).get();
        route.setOrigin(this.airportsRepository.findByIata(aitaAirportOrigin).get());
        route.setDestination(this.airportsRepository.findByIata(aitaAirportDestination).get());
        route.setDistance(distance);
        this.routeRepository.save(route);
    }

    public List getAllRoutes(){

        List<RouteResponse> response = new ArrayList<>();
        this.routeRepository.findAll().forEach(c->response.add(new RouteResponse(c)));
        return response;
    }
    public List getAirportsWhitOriginRoutes(){
        List<Route> routes = this.routeRepository.findAll();
        List<Airports> airportWhitOrigin = new ArrayList<>();
        for (Route route: routes) {
            if (!airportWhitOrigin.contains(route.getOrigin())){
                airportWhitOrigin.add(route.getOrigin());
            }
        }
        List<AirportResponse> res = new ArrayList<>();
        airportWhitOrigin.forEach(c->res.add(new AirportResponse(c)));
        return res;
    }
    public List getDestinationAirportsForOrigin(String airportIataOrigin){
        Airports originAirport = this.airportsRepository.findByIata(airportIataOrigin).get();
        List<AirportResponse> airportsResponse;
        if(originAirport!=null){
            List<Route> routes = this.routeRepository.findAll();
            List<Airports> airports = new ArrayList<>();
            for (Route route:routes) {
                if (route.getOrigin().equals(originAirport)){
                    airports.add(route.getDestination());
                }
            }
            airportsResponse = new ArrayList<>();
            airports.forEach(c->airportsResponse.add(new AirportResponse(c)));
        }else{
            airportsResponse = null;
        }
        return airportsResponse;
    }

    public Optional<Route> getById(long id){

        return this.routeRepository.findById(id);
    }

    public Route getRouteByAirportIataOriginAndDestination(String iataOrigin, String iataDestination){
        Optional<Airports> origin = this.airportsRepository.findByIata(iataOrigin);
        Optional<Airports> destination = this.airportsRepository.findByIata(iataDestination);
        Optional<Route> returnRoute = Optional.empty();
        if (origin.isPresent() && destination.isPresent())
        {
            returnRoute = this.routeRepository.findByOriginAndDestination(origin.get(),destination.get());
        }
        return returnRoute.isPresent()?returnRoute.get():null;
    }





}
