package adapter.out.persistence;

import br.com.gubee.interview.model.request.UpdateHeroRequest;
import domain.PowerStats;

import java.util.UUID;

public interface PowerStatsRepository {
    public UUID createPowerStats(PowerStats powerStats);
    public void deletePowerStatsById(UUID id);
    public void updatePowerStatsById(UUID id, UpdateHeroRequest updateHeroRequest);
}
