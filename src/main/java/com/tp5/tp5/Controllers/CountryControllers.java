package com.tp5.tp5.Controllers;
import com.models.Models.Country;
import com.models.payload.request.CountryRequest;
import com.tp5.tp5.Services.CountryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.util.List;

@RestController
@NoArgsConstructor @AllArgsConstructor
@RequestMapping("/country")
public class CountryControllers {

    @Autowired
    private CountryService countryService;


    @DeleteMapping("{id}")
    public ResponseEntity deleteCountry(@PathVariable Long id) {
        ResponseEntity response;
        try {
            this.countryService.deleteCountry(id);
            response = new ResponseEntity(HttpStatus.OK);

        } catch (PersistenceException pe) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity addCountry(@RequestBody CountryRequest countryRequest) {
        try {
            this.countryService.saveCountry(new Country(countryRequest.getName(), countryRequest.getCode()));
        } catch (PersistenceException  pe) {
            new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public void modifyCountry(@RequestBody CountryRequest countryRequest) {

        this.countryService.modifyCountry(countryRequest.getCode(), countryRequest.getName(), countryRequest.getId());
    }

    @GetMapping
    public List getAll() {
        return this.countryService.getAllCountrys();
    }
}
