package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PowerStatsService {

    private final PowerStatsRepository powerStatsRepository;

    @Transactional
    public UUID create(PowerStats powerStats) {
        return powerStatsRepository.create(powerStats);
    }

    @Transactional
    public void updateById(UUID id, UpdateHeroRequest updateHeroRequest) throws EmptyResultDataAccessException {
        powerStatsRepository.updatePowerStatsById(id, updateHeroRequest);
    }

    @Transactional
    public ResponseEntity<Object> deletePowerStats(UUID id) {
        try {
            powerStatsRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Hero not found", HttpStatus.NOT_FOUND);
        }
    }
}
