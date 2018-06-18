package com.tp5.tp5.Models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cabin_route")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @AllArgsConstructor
public class Cabin_Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false,nullable = false)
    private long id;


    @ManyToOne
    @JoinColumn(name = "cabin_id")
    private Cabin cabin;


    @ManyToOne
    @JoinColumn(name = "route_id")
    private  Route route;


    @OneToMany(mappedBy="cabin_Route")
    private Set<Price> priceList = new HashSet<>();


    public Cabin_Route(Cabin cabin, Route route){
        this.cabin=cabin;
        this.route=route;
    }
}
