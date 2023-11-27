package dto;
import domain.model.Race;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

public record HeroDTO(
        @NotNull
        String name,
        @NotNull
        Race race,
        @NotNull
        UUID powerStatsId
) {
}
