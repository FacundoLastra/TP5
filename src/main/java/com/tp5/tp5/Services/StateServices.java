package com.tp5.tp5.Services;

import com.models.Models.Country;
import com.models.Models.State;
import com.models.payload.response.StateResponse;
import com.tp5.tp5.Repository.CountryRepository;
import com.tp5.tp5.Repository.StateRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @NoArgsConstructor
public class StateServices {
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CountryRepository countryRepository;

    public StateServices(StateRepository stateRepository, CountryRepository countryRepository) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    public boolean saveState (String iataCode, String name, String codeCountry) {
        State state = null;
        Country country = this.countryRepository.findByCode(codeCountry).get();
        if (country!=null){
            state = this.stateRepository.save(new State(country, iataCode, name));
        }
        return state!=null?true:false;
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
