package application.service;

import application.port.in.CreatePowerStatsUseCase;
import application.port.out.CreatePowerStatsPort;
import domain.model.PowerStats;
import dto.PowerStatsDto;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
public class CreatePowerStatsService implements CreatePowerStatsUseCase {

    private final CreatePowerStatsPort createPowerStatsPort;
    @Override
    public UUID create(PowerStatsDto powerStatsDto) {
        PowerStats powerStats = createPowerStatsDomain(powerStatsDto);
        return createPowerStatsPort.createPowerStats(powerStats);
    }

    private PowerStats createPowerStatsDomain (PowerStatsDto powerStatsDto) {
        return new PowerStats(null, powerStatsDto.strength(),
                powerStatsDto.agility(),
                powerStatsDto.dexterity(),
                powerStatsDto.intelligence(),
                Instant.now(),
                Instant.now()
        );
    }
}
