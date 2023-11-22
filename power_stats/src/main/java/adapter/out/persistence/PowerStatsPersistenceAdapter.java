package adapter.out.persistence;

import application.port.out.CreatePowerStatsPort;
import application.port.out.DeletePowerStatsPort;
import application.port.out.UpdatePowerStatsPort;
import domain.model.PowerStats;
import domain.model.WebPowerStats;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class PowerStatsPersistenceAdapter implements CreatePowerStatsPort, UpdatePowerStatsPort, DeletePowerStatsPort{
    private final PowerStatsRepository powerStatsRepository;

    @Override
    public UUID createPowerStats(PowerStats powerStats) {
        return powerStatsRepository.createPowerStats(powerStats);
    }

    @Override
    public void deletePowerStatsById(UUID id) {
        powerStatsRepository.deletePowerStatsById(id);
    }

    @Override
    public void updatePowerStatsById(UUID id, WebPowerStats webPowerStats) {
        powerStatsRepository.updatePowerStatsById(id, webPowerStats);
    }
}
