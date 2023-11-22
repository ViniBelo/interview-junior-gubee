package application.port.out;

import domain.model.PowerStats;

import java.util.UUID;

public interface CreatePowerStatsPort {
    public UUID createPowerStats(PowerStats powerStats);
}
