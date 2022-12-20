package io.eronalves.pawpals.repositories;

import org.springframework.data.repository.CrudRepository;

import io.eronalves.pawpals.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
