package application.service;

import application.port.in.FetchPowerStatsUseCase;
import application.port.out.FetchPowerStatsPort;
import dto.PowerStatsDto;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class FindPowerStatsService implements FetchPowerStatsUseCase {
    private final FetchPowerStatsPort fetchPowerStatsPort;
    @Override
    public PowerStatsDto findPowerStatsById(UUID id) {
        return fetchPowerStatsPort.findPowerStatsById(id);
    }
}
