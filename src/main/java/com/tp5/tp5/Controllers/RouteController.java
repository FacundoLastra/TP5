package com.tp5.tp5.Controllers;

import com.tp5.tp5.Models.Route;
import com.tp5.tp5.Services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PutMapping("/")
    public boolean addRoute(@PathVariable String aitaAirportOrigin,@PathVariable String aitaAirportDestination, @PathVariable float distance){
        return this.routeService.saveRoute(aitaAirportOrigin,aitaAirportDestination,distance);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRoute(@PathVariable("id") long idRoute){
        this.routeService.deleteRoute(idRoute);
    }

    @PatchMapping("/")
    public void updateRoute(@PathVariable long idRoute, @PathVariable String aitaAirportOrigin, @PathVariable String aitaAirportDestination, @PathVariable float distance){
        this.routeService.updateRoute(idRoute,aitaAirportOrigin,aitaAirportDestination,distance);
    }

    @GetMapping("/")
    public List getAllRoutes(){
        return this.routeService.getAllRoutes();
    }


///finished, but we don't testing this controller



}
