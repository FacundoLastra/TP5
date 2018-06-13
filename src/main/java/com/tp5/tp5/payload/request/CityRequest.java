package com.tp5.tp5.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter @Setter
public class CityRequest {

    @NotNull
    String iata;
    @NotNull
    String name;
    @NotNull
    String stateCode;
    int id;


}
