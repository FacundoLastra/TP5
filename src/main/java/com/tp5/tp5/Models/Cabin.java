package com.tp5.tp5.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy="price", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Price> priceList = new ArrayList<>();


    public Cabin(String name){
        this.name=name;
    }

}
