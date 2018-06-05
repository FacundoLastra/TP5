package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Country;
import com.tp5.tp5.Models.State;
import com.tp5.tp5.Repository.CountryRepository;
import com.tp5.tp5.Repository.StateRepository;
import com.tp5.tp5.payload.response.StateResponse;
import com.tp5.tp5.utilitys.CreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StateServices {


    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CountryRepository countryRepository;


    public void saveState (String iataCode, String name, String codeCountry) {

        Country country = this.countryRepository.findByCode(codeCountry).get();

        this.stateRepository.save(new State(country, iataCode, name));
    }

    public void deleteState (Long id) {

        this.stateRepository.deleteById(id);
    }

    public void modifyState (String iataCode, String name, String codeCountry) {

        State state = this.stateRepository.findByIata(iataCode).get();

        Country country = this.countryRepository.findByCode(codeCountry).get();

        state.setName(name);
        state.setIata(iataCode);
        state.setNation(country);

        this.stateRepository.save(state);
    }

    public List getAllStates () {
        List<StateResponse> response = new ArrayList<>();
        this.stateRepository.findAll().forEach(c->response.add(new StateResponse(c)));
        return response;
    }
}
