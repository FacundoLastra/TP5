package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.PriceService;
import com.tp5.tp5.payload.request.PriceRequest;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PutMapping
    public void addPrice(@RequestBody PriceRequest priceRequest){
        this.priceService.savePrice(priceRequest.getIdCabin(),priceRequest.getPrice(), priceRequest.getDesde(), priceRequest.getHasta());
    }

    @DeleteMapping("{id}")
    public void deletePrice(@PathVariable long idPrice){
        this.priceService.deletePrice(idPrice);
    }

    @PostMapping
    public void updatePrice(@RequestBody PriceRequest priceRequest){
        this.priceService.updatePrice(priceRequest.getId(),priceRequest.getPrice(), priceRequest.getDesde(), priceRequest.getHasta());
    }

    @GetMapping
    public List getAllPrices(){
        return this.priceService.getAllPrices();
    }
}
