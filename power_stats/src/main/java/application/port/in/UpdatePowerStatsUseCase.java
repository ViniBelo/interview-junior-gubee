package application.port.in;

import br.com.gubee.interview.model.request.UpdateHeroRequest;

import java.util.UUID;

public interface UpdatePowerStatsUseCase {
    public void updateById(UUID id, UpdateHeroRequest updateHeroRequest);
}
