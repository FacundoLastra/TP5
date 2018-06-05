package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.CityService;
import com.tp5.tp5.payload.request.CityRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/city")
public class CityControllers {

    @Autowired
    private CityService cityService;


    @DeleteMapping("{id}")
    public void deleteCity (@PathVariable Long id) {

        this.cityService.deleteCity(id);
    }

    @PutMapping
    public void addCity (@RequestBody CityRequest cityRequest) {

        try {

            this.cityService.saveCity(cityRequest.getIata(), cityRequest.getName(), cityRequest.getStateCode());

        } catch (Exception e) {

        }
    }

    @PostMapping("/")
    public void modifyCity (@RequestBody CityRequest cityRequest){

        try {

            this.cityService.modifyCity(cityRequest.getIata(), cityRequest.getName(), cityRequest.getStateCode());

        } catch (Exception e) {

        }
    }

    @GetMapping
    public List getAll(){
        return this.cityService.getAllCitys();
    }

}
