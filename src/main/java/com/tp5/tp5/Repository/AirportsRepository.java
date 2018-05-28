package com.tp5.tp5.Repository;

import com.tp5.tp5.Models.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportsRepository extends JpaRepository<Airports, Long> {

    Optional<Airports> findByIata (@Param("iata")String iata);
}
