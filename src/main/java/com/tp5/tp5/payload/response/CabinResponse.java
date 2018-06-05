package com.tp5.tp5.payload.response;

import com.tp5.tp5.Models.Cabin;
import lombok.Getter;

@Getter
public class CabinResponse {
    //private int id;
    private String name;
    //private List<PriceResponse> priceList;
    public CabinResponse(Cabin cabin){
        this.name = cabin.getName();
    }
}
