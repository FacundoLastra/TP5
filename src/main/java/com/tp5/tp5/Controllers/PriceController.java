package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.PriceService;
import com.tp5.tp5.payload.request.PriceRequest;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/price")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PutMapping
    public void addPrice(@RequestBody PriceRequest priceRequest){
        this.priceService.savePrice(priceRequest.getIdCabin(),priceRequest.getIdRoute(),priceRequest.getPrice(), priceRequest.getDesde(), priceRequest.getHasta());
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
