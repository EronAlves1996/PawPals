package io.eronalves.pawpals.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.eronalves.pawpals.entities.AnimalSize;
import io.eronalves.pawpals.entities.AnimalType;
import io.eronalves.pawpals.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("UPDATE User u SET u.isLookingForAnimal = true, u.preferences.color = :color, u.preferences.isAdult = :isAdult, u.preferences.size = :size, u.preferences.type = :type WHERE u.id = :id")
    @Modifying
    void updateAdoptionPreference(@Param("color") String color, @Param("isAdult") boolean isAdult,
            @Param("size") AnimalSize size, @Param("type") AnimalType type, int id);
}
