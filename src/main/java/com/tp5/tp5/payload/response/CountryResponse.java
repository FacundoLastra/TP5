package com.tp5.tp5.payload.response;

import com.tp5.tp5.Models.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CountryResponse {
    String name;
    String code;

    public CountryResponse(Country country){
        this.name = country.getName();
        this.code = country.getCode();
    }

}
