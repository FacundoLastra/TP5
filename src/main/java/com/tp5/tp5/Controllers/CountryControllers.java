package com.tp5.tp5.Controllers;
import com.tp5.tp5.Services.CountryService;
import com.tp5.tp5.payload.request.CountryRequest;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;


@RestController
@RequestMapping("/country")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class CountryControllers {

    @Autowired
    private CountryService countryService;


    @DeleteMapping("{id}")
    public void deleteCountry (@PathVariable Long id) {

        this.countryService.deleteCountry(id);
    }

    //@RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @PutMapping
    public void addCountry (@RequestBody CountryRequest countryRequest){
        try {
            this.countryService.saveCountry(countryRequest.getName(), countryRequest.getCode());
        }catch (Exception e)
        {
        }

    }
    @PostMapping("/update")
    public void modifyCountry (@RequestBody CountryRequest countryRequest) {

        this.countryService.modifyCountry(countryRequest.getCode(), countryRequest.getName(),countryRequest.getId());
    }
    @GetMapping
    public List getAll(){

        return this.countryService.getAllCountrys();
    }
}
