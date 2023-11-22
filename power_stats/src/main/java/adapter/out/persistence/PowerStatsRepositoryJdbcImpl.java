package adapter.out.persistence;

import application.port.out.CreatePowerStatsPort;
import application.port.out.DeletePowerStatsPort;
import application.port.out.UpdatePowerStatsPort;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import domain.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PowerStatsRepositoryJdbcImpl implements PowerStatsRepository{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String CREATE_POWER_STATS_QUERY = "INSERT INTO power_stats" +
            " (strength, agility, dexterity, intelligence)" +
            " VALUES (:strength, :agility, :dexterity, :intelligence) RETURNING id";

    public UUID createPowerStats(PowerStats powerStats) {
        return namedParameterJdbcTemplate.queryForObject(
                CREATE_POWER_STATS_QUERY,
                new BeanPropertySqlParameterSource(powerStats),
                UUID.class);
    }
    private static final String DELETE_POWER_STATS_QUERY =
            "DELETE " +
                    "FROM power_stats " +
                    "WHERE id = :id";

    public void deletePowerStatsById(UUID id) {
        final Map<String, Object> params = Map.of("id", id);
        namedParameterJdbcTemplate.update(
                DELETE_POWER_STATS_QUERY,
                params
        );
    }
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
