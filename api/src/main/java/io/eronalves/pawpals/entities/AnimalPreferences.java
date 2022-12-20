package io.eronalves.pawpals.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class AnimalPreferences {

    @NotNull
    @Size(max = 15)
    private String color;

    @NotNull
    private boolean isAdult;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AnimalSize size;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AnimalType type;

}
