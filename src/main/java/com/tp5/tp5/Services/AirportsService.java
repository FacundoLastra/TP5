package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Airports;
import com.tp5.tp5.Models.City;
import com.tp5.tp5.Repository.AirportsRepository;
import com.tp5.tp5.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportsService {

    @Autowired
    private AirportsRepository airportsRepository;
    private CityRepository cityRepository;

    public void saveAirport (String iata, String name, String codeCity, float longitud, float latitud) {

        City city = this.cityRepository.findByCode(codeCity).get();

        this.airportsRepository.save(new Airports(iata, name, city, longitud, latitud));
    }

    public void deleteAirport (Long id) {

        this.airportsRepository.deleteById(id);
    }

    public void modifyAirport (String iata, String name, String codeCity, float longitud, float latitud) {

        City city = this.cityRepository.findByCode(codeCity).get();

        Airports airports = this.airportsRepository.findByIata(iata).get();

        airports.setCity(city);
        airports.setIata(iata);
        airports.setLatitud(latitud);
        airports.setLongitud(longitud);
        airports.setName(name);

        this.airportsRepository.save(airports);
    }
}
