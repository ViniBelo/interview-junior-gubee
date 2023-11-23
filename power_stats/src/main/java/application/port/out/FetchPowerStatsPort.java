package application.port.out;

import dto.PowerStatsDto;

import java.util.UUID;

public interface FetchPowerStatsPort {
    PowerStatsDto findPowerStatsById(UUID id);
}
