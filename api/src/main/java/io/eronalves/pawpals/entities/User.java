package io.eronalves.pawpals.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class User {

    @Id
    private int id;

    @NotNull
    @Size(max = 30)
    private String userName;

    @Email
    @NotNull
    private String email;

    @NotNull
    private boolean isLookingForAnimal;

    @Embedded
    private AnimalPreferences preferences;
}
