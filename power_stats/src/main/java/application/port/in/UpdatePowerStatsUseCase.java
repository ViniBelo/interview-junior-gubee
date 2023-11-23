package application.port.in;

import dto.PowerStatsDto;

import java.util.UUID;

public interface UpdatePowerStatsUseCase {
    void updateById(UUID id, PowerStatsDto powerStatsDto);
}
