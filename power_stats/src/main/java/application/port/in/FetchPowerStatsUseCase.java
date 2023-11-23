package application.port.in;

import dto.PowerStatsDto;

import java.util.UUID;

public interface FetchPowerStatsUseCase {
    PowerStatsDto findPowerStatsById(UUID id);
}
