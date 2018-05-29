package com.tp5.tp5.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="country",
        uniqueConstraints = {@UniqueConstraint(columnNames={"code"})})
@Getter
@Setter
@NoArgsConstructor
public class Country {

    public Country(String name, String code){

        this.name = name;
        this.code = code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false,nullable = false)
    private long id;
    String name;
    String code;
    @OneToMany(mappedBy="nation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<State> stateList = new ArrayList<>();
}
