package com.tp5.tp5.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AirportRequest {

    @NotNull
    String iata;
    @NotNull
    String name;
    @NotNull
    String cityCode;
    @NotNull
    float longitud;
    @NotNull
    float latitud;
    int id;
}
