package adapter.out;

import adapter.out.persistence.PowerStatsRepository;
import application.port.out.CreatePowerStatsPort;
import application.port.out.DeletePowerStatsPort;
import application.port.out.UpdatePowerStatsPort;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import domain.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class PowerStatsPersistenceAdapter implements CreatePowerStatsPort, UpdatePowerStatsPort, DeletePowerStatsPort{
    private final PowerStatsRepository powerStatsRepository;

    @Override
    @Transactional
    public UUID createPowerStats(PowerStats powerStats) {
        return powerStatsRepository.createPowerStats(powerStats);
    }

    @Override
    @Transactional
    public void deletePowerStatsById(UUID id) {
        powerStatsRepository.deletePowerStatsById(id);
    }

    @Override
    @Transactional
    public void updatePowerStatsById(UUID id, UpdateHeroRequest updateHeroRequest) {
        powerStatsRepository.updatePowerStatsById(id, updateHeroRequest);
    }
}
