package application.service;

import application.port.in.DeletePowerStatsUseCase;
import application.port.out.DeletePowerStatsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletePowerStatsService implements DeletePowerStatsUseCase {

    private final DeletePowerStatsPort deletePowerStatsRepository;
    @Transactional
    public void deletePowerStats(UUID id) {
        deletePowerStatsRepository.deletePowerStatsById(id);
    }
}
