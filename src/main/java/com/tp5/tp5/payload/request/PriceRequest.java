package com.tp5.tp5.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter @Setter
public class PriceRequest {

    @NotNull
    int idCabin;
    @NotNull
    int idRoute;
    @NotNull
    float price;
    @NotNull
    String desde;
    @NotNull
    String hasta;
    int id;


}
