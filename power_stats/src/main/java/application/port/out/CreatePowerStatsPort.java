package application.port.out;

import domain.model.PowerStats;
import dto.PowerStatsDto;

import java.util.UUID;

public interface CreatePowerStatsPort {
    UUID createPowerStats(PowerStats powerStats);
}
