package com.tp5.tp5.Controllers;

import com.tp5.tp5.Models.Cabin;
import com.tp5.tp5.Services.CabinService;
import com.tp5.tp5.Services.RouteService;
import com.tp5.tp5.payload.request.CabinRequest;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/cabin")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
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
    public void deleteCabin(@PathVariable long id){
        this.cabinService.deleteCabin(id);

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
