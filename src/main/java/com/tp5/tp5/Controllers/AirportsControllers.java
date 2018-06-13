package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.AirportsService;
import com.tp5.tp5.payload.request.AirportRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/airports")
public class AirportsControllers {

    @Autowired
    private AirportsService airportsService;


    @DeleteMapping("{id}")
    public void deleteAirport (@PathVariable Long id) {

        this.airportsService.deleteAirport(id);
    }

    @PutMapping
    public void addAirport (@RequestBody AirportRequest airportRequest) {

        try {

            this.airportsService.saveAirport(airportRequest.getIata(), airportRequest.getName(), airportRequest.getCityCode(), airportRequest.getLongitud(), airportRequest.getLatitud());

        } catch (Exception e) {

        }
    }

    @PostMapping("/")
    public void modifyAirport (@RequestBody AirportRequest airportRequest) {

        try {

            this.airportsService.modifyAirport(airportRequest.getIata(), airportRequest.getName(), airportRequest.getCityCode(), airportRequest.getLongitud(), airportRequest.getLatitud());

        } catch (Exception e) {

        }
    }

    @GetMapping
    public List getAll() {

        return this.airportsService.getAllAirports();
    }
}
