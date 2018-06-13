package com.tp5.tp5.Repository;


import com.tp5.tp5.Models.Airports;
import com.tp5.tp5.Models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

}
