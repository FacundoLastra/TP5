package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.CityService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@NoArgsConstructor
@RequestMapping("/city")
public class CityControllers {

    @Autowired
    private CityService cityService;


    @DeleteMapping("/")
    public void deleteCity (@PathVariable Long id) {

        this.cityService.deleteCity(id);
    }

    @PutMapping("/")
    public void addCity (@PathVariable String iata, @PathVariable String name, @PathVariable String stateCode) {

        this.cityService.saveCity(iata, name, stateCode);
    }

    @PatchMapping("/")
    public void modifyCity (@PathVariable String iata, @PathVariable String name, @PathVariable String stateCode){

        this.cityService.modifyCity(iata, name, stateCode);
    }
}
