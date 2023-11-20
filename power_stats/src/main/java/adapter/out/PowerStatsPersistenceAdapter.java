package adapter.out;

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
    private final CreatePowerStatsPort createPowerStatsPort;
    private final UpdatePowerStatsPort updatePowerStatsPort;
    private final DeletePowerStatsPort deletePowerStatsPort;

    @Override
    @Transactional
    public UUID createPowerStats(PowerStats powerStats) {
        return createPowerStatsPort.createPowerStats(powerStats);
    }

    @Override
    @Transactional
    public void deletePowerStatsById(UUID id) {
        deletePowerStatsPort.deletePowerStatsById(id);
    }

    @Override
    @Transactional
    public void updatePowerStatsById(UUID id, UpdateHeroRequest updateHeroRequest) {
        updatePowerStatsPort.updatePowerStatsById(id, updateHeroRequest);
    }
}
