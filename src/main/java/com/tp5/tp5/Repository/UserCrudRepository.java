package com.tp5.tp5.Repository;

import com.tp5.tp5.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/***
 * This model Class, is a first test in Spring Security.
 *
 * User security operations like login and logout, and CRUD operations on User.
 * The User crud API is responsible of storing users somewhere.
 */

@Repository
public interface UserCrudRepository extends JpaRepository<User, Long> {


    Optional<User> find(@Param("id")String id);

}