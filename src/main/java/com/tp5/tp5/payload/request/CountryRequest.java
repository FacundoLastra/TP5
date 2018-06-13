package com.tp5.tp5.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter @Setter
public class CountryRequest {

    @NotNull
    String name;
    @NotNull
    String code;
    int id;
}
