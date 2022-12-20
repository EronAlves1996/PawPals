package io.eronalves.pawpals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.eronalves.pawpals.entities.Animal;
import io.eronalves.pawpals.entities.AnimalColor;
import io.eronalves.pawpals.entities.AnimalSize;
import io.eronalves.pawpals.entities.AnimalType;
import io.eronalves.pawpals.entities.User;
import jakarta.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("UPDATE User u SET u.isLookingForAnimal = true, u.preferences.color = :color, u.preferences.isAdult = :isAdult, u.preferences.size = :size, u.preferences.type = :type WHERE u.id = :id")
    @Modifying
    @Transactional
    void updateAdoptionPreference(@Param("color") AnimalColor color, @Param("isAdult") boolean isAdult,
            @Param("size") AnimalSize size, @Param("type") AnimalType type, @Param("id") int id);

    @Query("FROM Animal a WHERE a.color = ?1 OR a.size = ?2 OR a.isAdult = ?3 OR a.type = ?4")
    List<Animal> findMatches(AnimalColor color, AnimalSize size, boolean isAdult, AnimalType type);
}
