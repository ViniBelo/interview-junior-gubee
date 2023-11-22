package application.port.in;

import domain.model.WebPowerStats;

import java.util.UUID;

public interface CreatePowerStatsUseCase {
    UUID create(WebPowerStats createHeroRequest);
}
