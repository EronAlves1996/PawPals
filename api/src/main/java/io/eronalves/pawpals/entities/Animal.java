package io.eronalves.pawpals.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AnimalColor color;

    @NotNull
    private boolean isAdult;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AnimalSize size;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AnimalType type;

}
