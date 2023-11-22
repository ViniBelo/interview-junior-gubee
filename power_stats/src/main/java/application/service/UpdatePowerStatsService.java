package application.service;

import application.port.in.UpdatePowerStatsUseCase;
import application.port.out.UpdatePowerStatsPort;
import domain.model.WebPowerStats;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UpdatePowerStatsService implements UpdatePowerStatsUseCase {

    private final UpdatePowerStatsPort updatePowerStatsPort;
    @Override
    public void updateById(UUID id, WebPowerStats webPowerStats) throws EmptyResultDataAccessException {
        updatePowerStatsPort.updatePowerStatsById(id, webPowerStats);
    }
}
