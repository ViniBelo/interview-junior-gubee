package application.port.in;

import br.com.gubee.interview.model.request.CreateHeroRequest;
import br.com.gubee.interview.model.request.UpdateHeroRequest;

import java.util.UUID;

public interface PowerStatsUseCases {
    public UUID create(CreateHeroRequest createHeroRequest);
    public void updateById(UUID id, UpdateHeroRequest updateHeroRequest);
    public void deletePowerStats (UUID id);
}
