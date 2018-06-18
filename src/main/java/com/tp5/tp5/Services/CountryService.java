package com.tp5.tp5.Services;

import com.models.Models.Country;
import com.models.payload.response.CountryResponse;
import com.tp5.tp5.Repository.CountryRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Service @NoArgsConstructor
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {

        this.countryRepository = countryRepository;
    }

    public boolean saveCountry (Country country) throws PersistenceException  {
        Country ret = this.countryRepository.save(country);
        return ret!=null?true:false;
    }

    public void deleteCountry (Long id) throws PersistenceException {

        this.countryRepository.deleteById(id);
    }

    public void modifyCountry (String code, String name ,long id) throws PersistenceException{

        Country country = this.countryRepository.findById(id).get();

        country.setName(name);
        country.setCode(code);

        this.countryRepository.save(country);
    }
    public List getAllCountrys() throws PersistenceException {
         List<CountryResponse> response = new ArrayList<>();
         this.countryRepository.findAll().forEach(c->response.add(new CountryResponse(c)));
         return response;
    }
}
