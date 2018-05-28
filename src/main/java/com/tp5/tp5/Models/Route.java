package com.tp5.tp5.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="route")
@Getter
@Setter
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false,nullable = false)
    private long id;

    private float distance;

    @ManyToOne
    @JoinColumn(name = "airportOrigin_id")
    private Airports origin;

    @ManyToOne
    @JoinColumn(name = "airportDestination_id")
    private Airports destination;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "cabin_route",
            joinColumns = { @JoinColumn(name = "route_id") },
            inverseJoinColumns = { @JoinColumn(name = "cabin_id") })
    private Set<Cabin> cabin = new HashSet<>();

    public Route (float distance,Airports origin,Airports destination){
        this.distance=distance;
        this.origin=origin;
        this.destination=destination;
    }

}
