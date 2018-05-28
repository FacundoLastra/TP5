package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.PriceService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PutMapping("/")
    public void addPrice(@PathVariable long idCabin,@PathVariable float price,@PathVariable DateTime desde,@PathVariable DateTime hasta){
        this.priceService.savePrice(idCabin, price, desde, hasta);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePrice(@PathVariable long idPrice){
        this.priceService.deletePrice(idPrice);
    }

    @PatchMapping("/")
    public void updatePrice(@PathVariable long priceId,@PathVariable float price,@PathVariable DateTime desde,@PathVariable DateTime hasta){
        this.priceService.updatePrice(priceId, price, desde, hasta);
    }

    @GetMapping("/")
    public List getAllPrices(){
        return this.priceService.getAllPrices();
    }
}
