package com.tp5.tp5.Controllers;

import com.tp5.tp5.Models.Cabin;
import com.tp5.tp5.Services.CabinService;
import com.tp5.tp5.Services.RouteService;
import com.tp5.tp5.payload.request.CabinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabin")
public class CabinController {

    @Autowired
    private CabinService cabinService;
    @Autowired
    private RouteService routeService;

    @PutMapping
    public void addCabin(@RequestBody CabinRequest cabinRequest){

        Cabin addedCabin = this.cabinService.saveCabin(cabinRequest.getName());

        if (addedCabin != null){
            this.routeService.addCabin(cabinRequest.getRouteId(),addedCabin.getId());
        }
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteCabin(@PathVariable long id){

        return this.cabinService.deleteCabin(id);
    }

    @PostMapping("/update")
    public void updateCabin(@RequestBody CabinRequest cabinRequest){
        this.cabinService.modifyCabin(cabinRequest.getId(),cabinRequest.getName());
    }

    @GetMapping
    public List getAllCabins(){
        return this.cabinService.getAllCabins();
    }


}
