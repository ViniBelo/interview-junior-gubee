package application.port.in;

import br.com.gubee.interview.model.request.CreateHeroRequest;
import br.com.gubee.interview.model.request.UpdateHeroRequest;

import java.util.UUID;

public interface CreatePowerStatsUseCase {
    public UUID create(CreateHeroRequest createHeroRequest);
}
