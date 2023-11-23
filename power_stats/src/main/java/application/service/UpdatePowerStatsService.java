package application.service;

import application.port.in.UpdatePowerStatsUseCase;
import application.port.out.UpdatePowerStatsPort;
import dto.PowerStatsDto;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdatePowerStatsService implements UpdatePowerStatsUseCase {

    private final UpdatePowerStatsPort updatePowerStatsPort;
    @Override
    public void updateById(UUID id, PowerStatsDto powerStatsDto) {
        updatePowerStatsPort.updatePowerStatsById(id, powerStatsDto);
    }
}
