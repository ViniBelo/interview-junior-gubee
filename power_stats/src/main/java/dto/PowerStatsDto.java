package dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record PowerStatsDto(
        @NotNull
        @Min(value = 0)
        @Max(value = 10)
        int strength,
        @NotNull
        @Min(value = 0)
        @Max(value = 10)
        int agility,
        @NotNull
        @Min(value = 0)
        @Max(value = 10)
        int dexterity,
        @NotNull
        @Min(value = 0)
        @Max(value = 10)
        int intelligence
) {
}
