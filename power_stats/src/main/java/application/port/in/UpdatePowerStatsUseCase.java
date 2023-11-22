package application.port.in;

import domain.model.WebPowerStats;

import java.util.UUID;

public interface UpdatePowerStatsUseCase {
    public void updateById(UUID id, WebPowerStats updateHeroRequest);
}
