package com.tp5.tp5.Repository;

import com.models.Models.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, Long> {

    Optional<Cabin> findByName (@Param("name")String name);
}
