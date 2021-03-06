package com.tp5.tp5.Controllers;

import com.models.payload.request.CityRequest;
import com.tp5.tp5.Services.CityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor @AllArgsConstructor
@RequestMapping("/city")
public class CityControllers {

    @Autowired
    private CityService cityService;


    @DeleteMapping("{id}")
    public void deleteCity(@PathVariable Long id) {

        this.cityService.deleteCity(id);
    }

    @PutMapping
    public void addCity(@RequestBody CityRequest cityRequest) {

        this.cityService.saveCity(cityRequest.getIata(), cityRequest.getName(), cityRequest.getStateCode());

    }

    @PostMapping("/")
    public void modifyCity(@RequestBody CityRequest cityRequest) {

        this.cityService.modifyCity(cityRequest.getIata(), cityRequest.getName(), cityRequest.getStateCode());

    }

    @GetMapping
    public List getAll() {

        return this.cityService.getAllCitys();
    }

}
