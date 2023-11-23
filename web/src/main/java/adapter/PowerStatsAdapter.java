package adapter;

import application.port.in.CreatePowerStatsUseCase;
import application.port.in.DeletePowerStatsUseCase;
import application.port.in.FetchPowerStatsUseCase;
import application.port.in.UpdatePowerStatsUseCase;
import dto.PowerStatsDto;
import lombok.AllArgsConstructor;
import request.CreateHeroRequest;

import java.util.UUID;

@AllArgsConstructor
public class PowerStatsAdapter {
    private final CreatePowerStatsUseCase createPowerStatsUseCase;
    private final UpdatePowerStatsUseCase updatePowerStatsUseCase;
    private final DeletePowerStatsUseCase deletePowerStatsUseCase;
    private final FetchPowerStatsUseCase fetchPowerStatsUseCase;

    public UUID create (CreateHeroRequest createHeroRequest) {
        PowerStatsDto powerStats = createPowerStatsDto(createHeroRequest);
        return createPowerStatsUseCase.create(powerStats);
    }

    public void updateById (UUID id, CreateHeroRequest createHeroRequest) {
        PowerStatsDto powerStats = createPowerStatsDto(createHeroRequest);
        updatePowerStatsUseCase.updateById(id, powerStats);
    }

    public PowerStatsDto findPowerStatsById(UUID id) {
        return fetchPowerStatsUseCase.findPowerStatsById(id);
    }

    private PowerStatsDto createPowerStatsDto (CreateHeroRequest createHeroRequest) {
        return new PowerStatsDto(
                createHeroRequest.getStrength(),
                createHeroRequest.getAgility(),
                createHeroRequest.getDexterity(),
                createHeroRequest.getIntelligence()
        );
    }

    public void deletePowerStats (UUID id) {
        deletePowerStatsUseCase.deletePowerStats(id);
    }
}
