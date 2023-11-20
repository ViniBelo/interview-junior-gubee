package adapter.out.persistence;

import application.port.out.DeletePowerStatsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DeletePowerStatsRepositoryJdbcImpl implements DeletePowerStatsPort {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
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
}
