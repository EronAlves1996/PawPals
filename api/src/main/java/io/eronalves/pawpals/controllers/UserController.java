package io.eronalves.pawpals.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eronalves.pawpals.entities.AnimalPreferences;
import io.eronalves.pawpals.entities.User;
import io.eronalves.pawpals.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/{id}/adoption")
    public User appointInterestToAdoption(@PathVariable("id") int id, AnimalPreferences preferences) {
        userRepository.updateAdoptionPreference(preferences.getColor(), preferences.isAdult(),
                preferences.getSize(), preferences.getType(), id);

        return userRepository.findById(id).get();
    }

}
