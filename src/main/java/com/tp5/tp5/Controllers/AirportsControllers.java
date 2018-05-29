package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.AirportsService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@NoArgsConstructor
@RequestMapping("/airports")
public class AirportsControllers {

    @Autowired
    private AirportsService airportsService;


    @DeleteMapping("/")
    public void deleteAirport (@PathVariable Long id) {

        this.airportsService.deleteAirport(id);
    }

    @PutMapping("/")
    public void addAirport (@PathVariable String iata, @PathVariable String name, @PathVariable String codeCity, @PathVariable float longitud, @PathVariable float latitud) {

        this.airportsService.saveAirport(iata, name, codeCity, longitud, latitud);
    }

    @PatchMapping("/")
    public void modifyAirport (@PathVariable String iata, @PathVariable String name, @PathVariable String codeCity, @PathVariable float longitud, @PathVariable float latitud) {

        this.airportsService.modifyAirport(iata, name, codeCity, longitud, latitud);
    }
}
