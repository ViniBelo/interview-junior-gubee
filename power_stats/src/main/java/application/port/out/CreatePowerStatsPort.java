package application.port.out;

import domain.PowerStats;

import java.util.UUID;

public interface CreatePowerStatsPort {
    public UUID createPowerStats(PowerStats powerStats);
}
