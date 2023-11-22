package adapter.out.persistence;

import br.com.gubee.interview.model.request.UpdateHeroRequest;
import domain.model.PowerStats;
import domain.model.WebPowerStats;

import java.util.UUID;

public interface PowerStatsRepository {
    public UUID createPowerStats(PowerStats powerStats);
    public void deletePowerStatsById(UUID id);
    public void updatePowerStatsById(UUID id, WebPowerStats webPowerStats);
}
