package com.tp5.tp5.Services;

import com.models.Models.Airports;
import com.models.Models.City;
import com.models.payload.response.AirportResponse;
import com.tp5.tp5.Repository.AirportsRepository;
import com.tp5.tp5.Repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class AirportsService {

    @Autowired
    private AirportsRepository airportsRepository;
    @Autowired
    private CityRepository cityRepository;

    public boolean saveAirport (String iata, String name, String codeCity, float longitud, float latitud) {

        City city = this.cityRepository.findByIata(codeCity).get();

        Airports airport= this.airportsRepository.save(new Airports(iata, name, city, longitud, latitud));

       return airport!=null?true:false;
    }

    public void deleteAirport (Long id) {

        this.airportsRepository.deleteById(id);
    }

    public void modifyAirport (String iata, String name, String codeCity, float longitud, float latitud) {

        Optional<City> city = this.cityRepository.findByIata(codeCity);
        Optional<Airports> optionalAirports = this.airportsRepository.findByIata(iata);
        if ( city.isPresent() && optionalAirports.isPresent() ) {
            Airports airports = optionalAirports.get();
            airports.setCity(city.get());
            airports.setIata(iata);
            airports.setLatitud(latitud);
            airports.setLongitud(longitud);
            airports.setName(name);

            this.airportsRepository.save(airports);
        }
    }

    public List getAllAirports () {

        List<AirportResponse> response = new ArrayList<>();
        this.airportsRepository.findAll().forEach(c->response.add(new AirportResponse(c)));
        return response;
    }
}
