package power_stats;

import application.port.out.CreatePowerStatsPort;
import application.port.out.DeletePowerStatsPort;
import application.port.out.UpdatePowerStatsPort;
import domain.model.PowerStats;
import dto.PowerStatsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import repositories.PowerStatsRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class PowerStatsPersistenceAdapter implements CreatePowerStatsPort, UpdatePowerStatsPort, DeletePowerStatsPort {
    private final PowerStatsRepository powerStatsRepository;

    @Transactional
    @Override
    public UUID createPowerStats(PowerStats powerStats) {
        return powerStatsRepository.createPowerStats(powerStats);
    }

    @Transactional
    @Override
    public void deletePowerStatsById(UUID id) {
        powerStatsRepository.deletePowerStatsById(id);
    }

    @Transactional
    @Override
    public void updatePowerStatsById(UUID id, PowerStatsDto powerStatsDto) {
        powerStatsRepository.updatePowerStatsById(id, powerStatsDto);
    }
}
