package application.port.out;

import br.com.gubee.interview.model.request.UpdateHeroRequest;

import java.util.UUID;

public interface UpdatePowerStatsPort {
    public void updatePowerStatsById(UUID id, UpdateHeroRequest updateHeroRequest);
}
