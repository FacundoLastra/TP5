package com.tp5.tp5.payload.response;

import com.tp5.tp5.Models.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class StateResponse {
    String iata;
    String name;
    CountryResponse country;

    public StateResponse(State state){
        this.iata = state.getIata();
        this.name = state.getName();
        this.country = new CountryResponse(state.getNation());
    }
}
