package com.tp5.tp5.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="cabin")
@Getter
@Setter
@NoArgsConstructor
public class Cabin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false,nullable = false)
    private long id;
    private String name;



    @OneToMany(mappedBy = "cabin", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cabin_Route> cabinRouteSet = new HashSet<>();

    public Cabin(String name){
        this.name=name;
    }

}
