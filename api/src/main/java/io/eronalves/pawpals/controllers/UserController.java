package io.eronalves.pawpals.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eronalves.pawpals.entities.Animal;
import io.eronalves.pawpals.entities.AnimalPreferences;
import io.eronalves.pawpals.entities.User;
import io.eronalves.pawpals.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(description = "Register a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "New User Registered", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    })
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @Operation(description = "Register adoption interests")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Adoption Interests registered", content = @Content(mediaType = "applicationjson", schema = @Schema(implementation = User.class)))
    })
    @PostMapping("/{id}/adoption")
    public User appointInterestToAdoption(@Parameter(description = "The id of the user") @PathVariable("id") int id,
            @RequestBody AnimalPreferences preferences) {
        userRepository.updateAdoptionPreference(preferences.getColor(), preferences.isAdult(),
                preferences.getSize(), preferences.getType(), id);

        return userRepository.findById(id).get();
    }

    @Operation(description = "Unregister adoption interests")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Adoption interests unregistered", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    })
    @PutMapping("/no-adopt/{id}")
    public User retireInterestToAdopt(@Parameter(description = "The id of the user") @PathVariable("id") int id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPreferences(null);
            user.setLookingForAnimal(false);
            return userRepository.save(user);
        }
        throw new ErrorResponseException(HttpStatus.NOT_FOUND);
    }

    @Operation(description = "Get a list of animals that match (even partially) the interests of user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "A list of animals that match the interests of user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Animal[].class)))
    })
    @GetMapping("/matches/{id}")
    public List<Animal> findMatches(@Parameter(description = "The id of the user") @PathVariable("id") int id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            if (!userOpt.get().isLookingForAnimal())
                throw new ErrorResponseException(HttpStatus.NOT_ACCEPTABLE);
            AnimalPreferences preferences = userOpt.get().getPreferences();
            return userRepository.findMatches(preferences.getColor(), preferences.getSize(), preferences.isAdult(),
                    preferences.getType());
        }
        throw new ErrorResponseException(HttpStatus.NOT_FOUND);
    }

}
