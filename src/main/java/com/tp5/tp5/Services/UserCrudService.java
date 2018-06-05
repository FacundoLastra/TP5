package com.tp5.tp5.Services;

import com.tp5.tp5.Repository.UserCrudRepository;
import com.tp5.tp5.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/***
 * This model Class, is a first test in Spring Security.
 */

@Service
final class UserCrudService {

    @Autowired
    UserCrudRepository userCrudRepository;


    public void saveUser(User user) {

        this.userCrudRepository.save(user);
    }


    public Optional<User> find(String id) {

        return userCrudRepository.find(id);
    }

}
