package io.eronalves.pawpals.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Animal {

    @Id
    private int id;

    @NotNull
    @Size(max = 30)
    private String name;

    @Size(max = 15)
    private String color;

    @NotNull
    private boolean isAdult;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AnimalSize size;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AnimalType type;

}
