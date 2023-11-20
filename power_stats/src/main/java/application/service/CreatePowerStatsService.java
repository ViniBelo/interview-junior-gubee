package application.service;

import application.port.in.CreatePowerStatsUseCase;
import application.port.out.CreatePowerStatsPort;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import domain.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePowerStatsService implements CreatePowerStatsUseCase {

    private final CreatePowerStatsPort createPowerStatsPort;
    @Transactional
    public UUID create(CreateHeroRequest createHeroRequest) {
        return createPowerStatsPort.createPowerStats(
                new PowerStats(null, createHeroRequest.getStrength(),
                        createHeroRequest.getAgility(),
                        createHeroRequest.getDexterity(),
                        createHeroRequest.getIntelligence(),
                        Instant.now(),
                        Instant.now()
                )
        );
    }
}
