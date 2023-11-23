package application.port.out;

import dto.PowerStatsDto;

import java.util.UUID;

public interface UpdatePowerStatsPort {
    void updatePowerStatsById(UUID id, PowerStatsDto powerStatsDto);
}
