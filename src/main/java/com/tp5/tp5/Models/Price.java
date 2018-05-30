package com.tp5.tp5.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name="price")
@Getter
@Setter
@NoArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false,nullable = false)
    private long id;

    private float price;

    private DateTime desde;

    private DateTime hasta;

    @ManyToOne
    @JoinColumn(name = "cabin_id")
    Cabin cabin;

    public Price(Float price, DateTime desde, DateTime hasta, Cabin cabin){
        this.price = price;
        this.desde = desde;
        this.hasta= hasta;
        this.cabin = cabin;
    }


}
