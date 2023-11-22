package application.service;

import application.port.in.CreatePowerStatsUseCase;
import application.port.out.CreatePowerStatsPort;
import domain.model.PowerStats;
import domain.model.WebPowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePowerStatsService implements CreatePowerStatsUseCase {

    private final CreatePowerStatsPort createPowerStatsPort;
    @Override
    public UUID create(WebPowerStats webPowerStats) {
        PowerStats powerStats = createPowerStatsDomain(webPowerStats);
        return createPowerStatsPort.createPowerStats(powerStats);
    }

    private PowerStats createPowerStatsDomain (WebPowerStats webPowerStats) {
        return new PowerStats(null, webPowerStats.getStrength(),
                webPowerStats.getAgility(),
                webPowerStats.getDexterity(),
                webPowerStats.getIntelligence(),
                Instant.now(),
                Instant.now()
        );
    }
}
