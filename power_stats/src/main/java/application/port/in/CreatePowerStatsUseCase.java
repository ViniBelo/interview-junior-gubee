package application.port.in;

import domain.model.WebPowerStats;

import java.util.UUID;

public interface CreatePowerStatsUseCase {
    public UUID create(WebPowerStats createHeroRequest);
}
