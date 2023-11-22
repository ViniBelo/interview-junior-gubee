package adapter.out.persistence;

import br.com.gubee.interview.model.request.UpdateHeroRequest;
import domain.model.PowerStats;
import domain.model.WebPowerStats;

import java.util.UUID;

public interface PowerStatsRepository {
    UUID createPowerStats(PowerStats powerStats);
    void deletePowerStatsById(UUID id);
    void updatePowerStatsById(UUID id, WebPowerStats webPowerStats);
}
