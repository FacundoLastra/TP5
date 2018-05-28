package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.RouteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private RouteService routeService;

    @PutMapping("/")
    public boolean addRoute(String aitaAirportOrigin, String aitaAirportDestination,float distance){
        return this.routeService.saveRoute(aitaAirportOrigin,aitaAirportDestination,distance);
    }

    @DeleteMapping("/")
    public void deleteRoute(long idRoute){
        this.routeService.deleteRoute(idRoute);
    }

}
