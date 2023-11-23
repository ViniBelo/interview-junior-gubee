package application.port.in;

import dto.PowerStatsDto;

import java.util.UUID;

public interface CreatePowerStatsUseCase {
    UUID create(PowerStatsDto powerStatsDto);
}
