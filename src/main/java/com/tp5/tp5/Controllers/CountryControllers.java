package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.CountryService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@NoArgsConstructor
@RequestMapping("/contry")
public class CountryControllers {

    @Autowired
    private CountryService countryService;


    @DeleteMapping("/")
    public void deleteCountry (@PathVariable Long id) {

        this.countryService.deleteCountry(id);
    }

    @PutMapping("/")
    public void addCountry (@PathVariable String name, @PathVariable String code){

        this.countryService.saveCountry(name, code);
    }

    @PatchMapping("/")
    public void modifyCountry (@PathVariable String name, @PathVariable String code) {

        this.countryService.modifyCountry(code, name);
    }
}
