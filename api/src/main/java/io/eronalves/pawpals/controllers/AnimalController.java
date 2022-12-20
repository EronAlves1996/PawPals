package io.eronalves.pawpals.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eronalves.pawpals.entities.Animal;
import io.eronalves.pawpals.repositories.AnimalRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private AnimalRepository repository;

    public AnimalController(AnimalRepository repository) {
        this.repository = repository;
    }

    @Operation(description = "Register a new Animal for adoption")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Animal registered", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Animal.class)))
    })
    @PostMapping("/register")
    public Animal registerAnimalForAdoption(@RequestBody Animal animal) {
        return repository.save(animal);
    }

    @Operation(description = "Get a list of all animals for adoption")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of animals", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Animal[].class)))
    })
    @GetMapping("/all")
    public Iterable<Animal> getAllAnimals() {
        return repository.findAll();
    }

    @Operation(description = "Adopt a animal and erased the registry in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Animal adopted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/adopt/{id}")
    public void adoptAnimal(@Parameter(description = "The id of the animal") @PathVariable("id") int id) {
        repository.deleteById(id);
    }
}
