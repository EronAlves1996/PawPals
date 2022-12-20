package io.eronalves.pawpals.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eronalves.pawpals.entities.Animal;
import io.eronalves.pawpals.repositories.AnimalRepository;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private AnimalRepository repository;

    public AnimalController(AnimalRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/register")
    public Animal registerAnimalForAdoption(@RequestBody Animal animal) {
        return repository.save(animal);
    }

    @GetMapping("/all")
    public Iterable<Animal> getAllAnimals() {
        return repository.findAll();
    }
}
