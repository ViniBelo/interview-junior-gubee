package dto;
import domain.model.Race;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public record HeroDTO(
        @NotNull
        String name,
        @NotNull
        String race,
        @NotNull
        UUID powerStatsId
) {
}
