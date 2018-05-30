package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.CountryService;
import com.tp5.tp5.payload.request.AirportRequest;
import com.tp5.tp5.payload.request.CountryRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@NoArgsConstructor
@RequestMapping("/country")
public class CountryControllers {

    @Autowired
    private CountryService countryService;


    @DeleteMapping("/")
    public void deleteCountry (@PathVariable Long id) {

        this.countryService.deleteCountry(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public void addCountry (@RequestBody @Valid @ModelAttribute CountryRequest countryRequest){

        this.countryService.saveCountry(countryRequest.getName(), countryRequest.getCode());
    }

    @PatchMapping("/")
    public void modifyCountry (@RequestBody @Valid @ModelAttribute CountryRequest countryRequest) {

        this.countryService.modifyCountry(countryRequest.getCode(), countryRequest.getName());
    }
    @GetMapping
    public void getAll(){
        this.countryService.getAllCountrys();
    }
}
