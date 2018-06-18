package com.tp5.tp5.Services;

import com.models.Models.City;
import com.models.Models.State;
import com.models.payload.response.CityResponse;
import com.tp5.tp5.Repository.CityRepository;
import com.tp5.tp5.Repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @AllArgsConstructor
public class CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StateRepository stateRepository;


    public boolean saveCity (String iata, String name, String codeState) {

        State state = this.stateRepository.findByIata(codeState).get();

       City city = this.cityRepository.save(new City(iata, name, state));

        return city!=null?true:false;
    }

    public void deleteCity (Long id) {

        this.cityRepository.deleteById(id);
    }

    public void modifyCity (String iata, String name, String codeState) {

        City city = this.cityRepository.findByIata(iata).get();

        State state = this.stateRepository.findByIata(codeState).get();

        city.setIata(iata);
        city.setName(name);
        city.setStateAtribute(state);

        this.cityRepository.save(city);
    }

    public List getAllCitys () {
        List<CityResponse> response = new ArrayList<>();
        this.cityRepository.findAll().forEach(c->response.add(new CityResponse(c)));
        return response;
    }
}
