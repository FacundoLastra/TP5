package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Airports;
import com.tp5.tp5.Models.Cabin;
import com.tp5.tp5.Models.Route;
import com.tp5.tp5.Repository.AirportsRepository;
import com.tp5.tp5.Repository.CabinRepository;
import com.tp5.tp5.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;
    private AirportsRepository airportsRepository;
    private CabinRepository cabinRepository;

    public boolean saveRoute(String aitaAirportOrigin, String aitaAirportDestination,float distance){
        boolean res=false;
        Airports airOrigin=this.airportsRepository.findByIata(aitaAirportOrigin).get();
        Airports airDestination=this.airportsRepository.findByIata(aitaAirportDestination).get();

        if(airOrigin!=null &&airDestination !=null){
        this.routeRepository.save(new Route(distance,airOrigin,airDestination));
        res = true;
        }

        return res;
    }
    public void deleteRoute(long id){

        this.routeRepository.deleteById(id);

    }

    public void addCabin(long idRoute, long idCabin){
        Route route = this.routeRepository.findById(idRoute).get();
        Cabin cabin = this.cabinRepository.findById(idCabin).get();

        if (route != null && cabin != null){
            if(!route.getCabin().contains(cabin)){
                route.getCabin().add(cabin);
            }
            this.routeRepository.save(route);
        }



    }




}
