package com.tp5.tp5.Models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name="price")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false,nullable = false)
    private long id;

    private float price;

    private LocalDate desde;

    private LocalDate hasta;

    @ManyToOne
    @JoinColumn(name = "cabin_route_id")
    Cabin_Route cabin_Route;

    public Price(Float price, String desde, String hasta, Cabin_Route cabin_Route ){
        this.price = price;
        this.setDesde(desde);
        this.setHasta(hasta);
        this.cabin_Route = cabin_Route;
    }
    private LocalDate stringToDateTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(time, formatter);
        return localDate;
    }
    public void setDesde(String desde){
        this.desde=this.stringToDateTime(desde);

    }
    public void setHasta(String hasta){
        this.hasta=this.stringToDateTime(hasta);
    }


}
