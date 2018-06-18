package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.PriceService;
import com.tp5.tp5.payload.request.PriceRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@AllArgsConstructor @NoArgsConstructor
@RequestMapping("/price")
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

    @GetMapping("/getCabinsAndPrices/iataOrigin/{iataOrigin}/iataDestination/{iataDestination}/fecha/{fecha}")
    public List getCabinsAndPrices (@PathVariable("iataOrigin") String iataOrigin, @PathVariable("iataDestination") String iataDestination, @PathVariable("fecha") String fecha){


        List priceResponse = this.priceService.getPriceWhitRouteAndDate(iataOrigin,iataDestination,this.stringToDateTime(fecha));
        return priceResponse;
    }

    private LocalDate stringToDateTime(String time) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(time, formatter);
        return localDate;
    }
}
