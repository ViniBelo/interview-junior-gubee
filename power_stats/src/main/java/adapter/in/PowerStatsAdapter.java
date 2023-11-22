package adapter.in;

import application.port.in.CreatePowerStatsUseCase;
import application.port.in.DeletePowerStatsUseCase;
import application.port.in.UpdatePowerStatsUseCase;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import domain.model.WebPowerStats;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class PowerStatsAdapter {
    private CreatePowerStatsUseCase createPowerStatsUseCase;
    private UpdatePowerStatsUseCase updatePowerStatsUseCase;
    private DeletePowerStatsUseCase deletePowerStatsUseCase;

    public UUID create (CreateHeroRequest createHeroRequest) {
        WebPowerStats powerStats = new WebPowerStats(
                createHeroRequest.getStrength(),
                createHeroRequest.getAgility(),
                createHeroRequest.getDexterity(),
                createHeroRequest.getIntelligence()
        );
        return createPowerStatsUseCase.create(powerStats);
    }

    public void updateById (UUID id, UpdateHeroRequest updateHeroRequest) {
        WebPowerStats powerStats = new WebPowerStats(
                updateHeroRequest.getStrength(),
                updateHeroRequest.getAgility(),
                updateHeroRequest.getDexterity(),
                updateHeroRequest.getIntelligence()
        );
        updatePowerStatsUseCase.updateById(id, powerStats);
    }

    public void deletePowerStats (UUID id) {
        deletePowerStatsUseCase.deletePowerStats(id);
    }
}
