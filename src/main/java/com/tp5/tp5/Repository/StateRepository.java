package com.tp5.tp5.Repository;

import com.tp5.tp5.Models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    Optional<State> findByIataCode (@Param("iata_code")String iataCode);
}
