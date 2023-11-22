package adapter.out.persistence;

import domain.model.PowerStats;
import domain.model.WebPowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PowerStatsRepositoryJdbcImpl implements PowerStatsRepository{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String CREATE_POWER_STATS_QUERY = "INSERT INTO power_stats" +
            " (strength, agility, dexterity, intelligence)" +
            " VALUES (:strength, :agility, :dexterity, :intelligence) RETURNING id";
    @Transactional
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
    @Transactional
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
    @Transactional
    public void updatePowerStatsById(UUID id, WebPowerStats webPowerStats) {
        final Map<String, Object> params = Map.of(
                "id", id,
                "strength", webPowerStats.getStrength(),
                "agility", webPowerStats.getAgility(),
                "dexterity", webPowerStats.getDexterity(),
                "intelligence", webPowerStats.getIntelligence()
        );
        namedParameterJdbcTemplate.update(
                UPDATE_POWER_STATS_QUERY,
                params
        );
    }
}
