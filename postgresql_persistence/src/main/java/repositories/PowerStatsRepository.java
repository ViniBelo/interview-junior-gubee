package repositories;

import domain.model.PowerStats;
import dto.PowerStatsDto;

import java.util.UUID;

public interface PowerStatsRepository {
    UUID createPowerStats(PowerStats powerStats);
    void deletePowerStatsById(UUID id);
    void updatePowerStatsById(UUID id, PowerStatsDto powerStatsDto);
}
