package com.tp5.tp5.Repository;

import com.models.Models.Airports;
import com.models.Models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByOriginAndDestination(Airports origin, Airports destination);
}
