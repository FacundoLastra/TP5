package com.tp5.tp5.Controllers;

import com.tp5.tp5.Models.Route;
import com.tp5.tp5.Services.RouteService;
import com.tp5.tp5.payload.request.RouteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PutMapping
    public boolean addRoute(@RequestBody RouteRequest routeRequest){

        return this.routeService.saveRoute(routeRequest.getIataAirportOrigin(),routeRequest.getIataAirportDestination(),routeRequest.getDistance());
    }

    @DeleteMapping("{id}")
    public void deleteRoute(@PathVariable("id") long idRoute){
        this.routeService.deleteRoute(idRoute);
    }

    @PostMapping("/update")
    public void updateRoute(@RequestBody RouteRequest routeRequest){
        this.routeService.updateRoute(routeRequest.getId(),routeRequest.getIataAirportOrigin(),routeRequest.getIataAirportDestination(),routeRequest.getDistance());
    }

    @GetMapping
    public List getAllRoutes(){
        return this.routeService.getAllRoutes();
    }





}
