package com.tp5.tp5.payload.response;

import com.tp5.tp5.Models.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class PriceResponse {
    private float price;
    private LocalDate desde;
    private LocalDate hasta;
    private RouteResponse route;
    private CabinResponse cabin;
    public PriceResponse(Price price){
        this.price = price.getPrice();
        this.desde = price.getDesde();
        this.hasta = price.getHasta();
        this.route = new RouteResponse(price.getCabin_Route().getRoute());
        this.cabin = new CabinResponse(price.getCabin_Route().getCabin());
    }
}
