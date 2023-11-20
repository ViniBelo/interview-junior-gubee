package application.service;

import application.port.in.UpdatePowerStatsUseCase;
import application.port.out.UpdatePowerStatsPort;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdatePowerStatsService implements UpdatePowerStatsUseCase {

    private final UpdatePowerStatsPort updatePowerStatsPort;
    @Override
    public void updateById(UUID id, UpdateHeroRequest updateHeroRequest) throws EmptyResultDataAccessException {
        updatePowerStatsPort.updatePowerStatsById(id, updateHeroRequest);
    }
}
