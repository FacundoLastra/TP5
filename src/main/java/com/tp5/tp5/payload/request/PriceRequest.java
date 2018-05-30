package com.tp5.tp5.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PriceRequest {

    @NotNull
    int idCabin;
    @NotNull
    float price;
    @NotNull
    DateTime desde;
    @NotNull
    DateTime hasta;
    int id;

}
