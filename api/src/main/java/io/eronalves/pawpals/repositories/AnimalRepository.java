package io.eronalves.pawpals.repositories;

import org.springframework.data.repository.CrudRepository;

import io.eronalves.pawpals.entities.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Integer> {

}
