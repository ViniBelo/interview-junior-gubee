package application.service;

import application.port.in.DeletePowerStatsUseCase;
import application.port.out.DeletePowerStatsPort;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeletePowerStatsService implements DeletePowerStatsUseCase {

    private final DeletePowerStatsPort deletePowerStatsPort;
    @Override
    public void deletePowerStats(UUID id) {
        deletePowerStatsPort.deletePowerStatsById(id);
    }
}
