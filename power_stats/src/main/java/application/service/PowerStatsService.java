package application.service;

import application.port.in.PowerStatsUseCases;
import application.port.out.CreatePowerStatsPort;
import application.port.out.DeletePowerStatsPort;
import application.port.out.UpdatePowerStatsPort;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import domain.PowerStats;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PowerStatsService implements PowerStatsUseCases {

    private final CreatePowerStatsPort createPowerStatsRepository;
    private final UpdatePowerStatsPort updatePowerStatsRepository;
    private final DeletePowerStatsPort deletePowerStatsRepository;

    @Transactional
    public UUID create(CreateHeroRequest createHeroRequest) {
        return createPowerStatsRepository.createPowerStats(
                new PowerStats(null, createHeroRequest.getStrength(),
                        createHeroRequest.getAgility(),
                        createHeroRequest.getDexterity(),
                        createHeroRequest.getIntelligence(),
                        Instant.now(),
                        Instant.now()));
    }

    @Transactional
    public void updateById(UUID id, UpdateHeroRequest updateHeroRequest) throws EmptyResultDataAccessException {
        updatePowerStatsRepository.updatePowerStatsById(id, updateHeroRequest);
    }

    @Transactional
    public void deletePowerStats(UUID id) {
        deletePowerStatsRepository.deletePowerStatsById(id);
    }
}
