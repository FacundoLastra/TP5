package com.tp5.tp5.Controllers;

import com.tp5.tp5.Models.Cabin;
import com.tp5.tp5.Services.CabinService;
import com.tp5.tp5.Services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabin")
public class CabinController {

    @Autowired
    private CabinService cabinService;
    @Autowired
    private RouteService routeService;

    @PutMapping("/")
    public void addCabin(@PathVariable long routeId , @PathVariable String name){
        Cabin addedCabin = this.cabinService.saveCabin(name);

        if (addedCabin != null){
            this.routeService.addCabin(routeId,addedCabin.getId());
        }
    }



    @DeleteMapping("/")
    public void deleteCabin(@PathVariable long id){
        this.cabinService.deleteCabin(id);

    }

    @PatchMapping("/")
    public void updateCabin(@PathVariable long id,@PathVariable String name){
        this.cabinService.modifyCabin(id,name);
    }

    @GetMapping("/")
    public List getAllCabins(){
        return this.cabinService.getAllCabins();
    }


}
