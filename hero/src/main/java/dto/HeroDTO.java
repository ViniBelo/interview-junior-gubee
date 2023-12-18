package dto;
import domain.model.Race;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

public record HeroDTO(
        UUID id,
        @NotNull
        String name,
        @NotNull
        Race race,
        @NotNull
        UUID powerStatsId
) {
//        public HeroDTO(UUID id, String name, Race race, UUID powerStatsId) {
//                this(id, name, race, powerStatsId);
//        }

        public HeroDTO(String name, Race race, UUID powerStatsId) {
                this(null, name, race, powerStatsId);
        }
}
