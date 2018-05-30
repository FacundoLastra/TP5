package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Country;
import com.tp5.tp5.Repository.CountryRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public void saveCountry (String name, String code) {

        this.countryRepository.save(new Country(name, code));
    }

    public void deleteCountry (Long id) {

        this.countryRepository.deleteById(id);
    }

    public void modifyCountry (String code, String name ,long id) {

        Country country = this.countryRepository.findById(id).get();

        country.setName(name);
        country.setCode(code);

        this.countryRepository.save(country);
    }
    public List getAllCountrys(){
        return this.countryRepository.findAll();
    }
}
