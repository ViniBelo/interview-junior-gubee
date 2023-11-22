package application.port.out;

import domain.model.WebPowerStats;

import java.util.UUID;

public interface UpdatePowerStatsPort {
    void updatePowerStatsById(UUID id, WebPowerStats webPowerStats);
}
