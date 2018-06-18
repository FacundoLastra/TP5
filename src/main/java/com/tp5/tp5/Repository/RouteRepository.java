package com.tp5.tp5.Repository;

import com.tp5.tp5.Models.Route;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByOriginAndDestination(Airports origin, Airports destination);
}
