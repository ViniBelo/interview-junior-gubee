package power_stats;

import data.builder.PowerStatsDataBuilder;
import domain.model.PowerStats;
import dto.PowerStatsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import repositories.PowerStatsRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PowerStatsRepositoryJdbcImpl implements PowerStatsRepository {
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
    public void updatePowerStatsById(UUID id, PowerStatsDto powerStatsDto) {
        final Map<String, Object> params = Map.of(
                "id", id,
                "strength", powerStatsDto.strength(),
                "agility", powerStatsDto.agility(),
                "dexterity", powerStatsDto.dexterity(),
                "intelligence", powerStatsDto.intelligence()
        );
        namedParameterJdbcTemplate.update(
                UPDATE_POWER_STATS_QUERY,
                params
        );
    }

    private static final String GET_STATS_BY_ID_QUERY =
            "SELECT ps.strength, ps.agility, ps.dexterity, ps.intelligence " +
                    "FROM power_stats ps " +
                    "WHERE id = :id";
    @Override
    public PowerStatsDataBuilder findPowerStatsById(UUID id) {
        final Map<String, Object> params = Map.of("id", id);
        return namedParameterJdbcTemplate.queryForObject(
                GET_STATS_BY_ID_QUERY,
                params,
                (resultSet, rowNumber) -> buildPowerStats(resultSet)
        );
    }

    private PowerStatsDataBuilder buildPowerStats (ResultSet resultSet) throws SQLException {
        var strength = resultSet.getInt("strength");
        var agility = resultSet.getInt("agility");
        var dexterity = resultSet.getInt("dexterity");
        var intelligence = resultSet.getInt("intelligence");
        return PowerStatsDataBuilder
                .builder()
                .strength(strength)
                .agility(agility)
                .dexterity(dexterity)
                .intelligence(intelligence)
                .build();
    }
}
