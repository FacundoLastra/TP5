package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.CountryService;
import com.tp5.tp5.payload.request.CountryRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/country")
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
