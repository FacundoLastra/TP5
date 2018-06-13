package com.tp5.tp5.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="state",
        uniqueConstraints = {@UniqueConstraint(columnNames={"iata"})})
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class State {
    public State (Country country, String iataCode, String name) {

        this.iata = iataCode;
        this.name = name;
        this.nation = country;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false,nullable = false)
    private long id;
    String iata;
    String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    Country nation;

    @OneToMany(mappedBy="stateAtribute",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("stateAtribute")
    private List<City> cityList;
}
