package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PowerStatsRepository {

    private static final String CREATE_POWER_STATS_QUERY = "INSERT INTO power_stats" +
        " (strength, agility, dexterity, intelligence)" +
        " VALUES (:strength, :agility, :dexterity, :intelligence) RETURNING id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    UUID create(PowerStats powerStats) {
        return namedParameterJdbcTemplate.queryForObject(
            CREATE_POWER_STATS_QUERY,
            new BeanPropertySqlParameterSource(powerStats),
            UUID.class);
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

    private static final String DELETE_POWER_STATS_QUERY =
            "DELETE " +
                    "FROM power_stats " +
                    "WHERE id = :id";

    public void delete(UUID id) {
        final Map<String, Object> params = Map.of("id", id);
        namedParameterJdbcTemplate.update(
                DELETE_POWER_STATS_QUERY,
                params
        );
    }
}
