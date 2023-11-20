package adapter.out.persistence;

import application.port.out.UpdatePowerStatsPort;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UpdatePowerStatsRepository implements UpdatePowerStatsPort {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public static final String UPDATE_POWER_STATS_QUERY =
            "UPDATE power_stats " +
                    "SET " +
                    "strength = :strength, " +
                    "agility = :agility, " +
                    "dexterity = :dexterity, " +
                    "intelligence = :intelligence " +
                    "WHERE id = :id";

    public void updatePowerStatsById(UUID id, UpdateHeroRequest updateHeroRequest) {
        final Map<String, Object> params = Map.of(
                "id", id,
                "strength", updateHeroRequest.getStrength(),
                "agility", updateHeroRequest.getAgility(),
                "dexterity", updateHeroRequest.getDexterity(),
                "intelligence", updateHeroRequest.getIntelligence()
        );
        namedParameterJdbcTemplate.update(
                UPDATE_POWER_STATS_QUERY,
                params
        );
    }
}
