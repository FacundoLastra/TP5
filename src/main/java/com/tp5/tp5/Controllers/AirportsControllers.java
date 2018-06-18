package com.tp5.tp5.Controllers;

import com.models.payload.request.AirportRequest;
import com.tp5.tp5.Services.AirportsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor @AllArgsConstructor
@RequestMapping("/airports")
public class AirportsControllers {

    @Autowired
    private AirportsService airportsService;


    @DeleteMapping("{id}")
    public void deleteAirport(@PathVariable Long id) {

        this.airportsService.deleteAirport(id);
    }

    @PutMapping
    public void addAirport(@RequestBody AirportRequest airportRequest) {

        this.airportsService.saveAirport(airportRequest.getIata(),
                airportRequest.getName(), airportRequest.getCityCode(),
                airportRequest.getLongitud(), airportRequest.getLatitud());

    }

    @PostMapping("/")
    public void modifyAirport(@RequestBody AirportRequest airportRequest) {

        this.airportsService.modifyAirport(airportRequest.getIata(), airportRequest.getName(),
                airportRequest.getCityCode(), airportRequest.getLongitud(), airportRequest.getLatitud());
    }

    @GetMapping
    public List getAll() {

        return this.airportsService.getAllAirports();
    }

}
