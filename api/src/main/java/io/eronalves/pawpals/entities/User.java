package io.eronalves.pawpals.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 30)
    private String userName;

    @Email
    @NotNull
    private String email;

    @NotNull
    private boolean isLookingForAnimal;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "color", column = @Column(name = "animal_color")),
            @AttributeOverride(name = "isAdult", column = @Column(name = "animal_is_adult")),
            @AttributeOverride(name = "size", column = @Column(name = "animal_size")),
            @AttributeOverride(name = "type", column = @Column(name = "animal_type"))
    })
    private AnimalPreferences preferences;
}
