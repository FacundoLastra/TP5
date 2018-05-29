package com.tp5.tp5.Repository;

import com.tp5.tp5.Models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Override
    Optional<City> findById(Long aLong);

    Optional<City> findByCode (@Param("code")String code);
}
