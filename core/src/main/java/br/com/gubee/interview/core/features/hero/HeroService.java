package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.connectors.HeroPowerStatsConnector;
import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import br.com.gubee.interview.model.response.CreateHeroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final PowerStatsService powerStatsService;
    private final HeroRepository heroRepository;

    @Transactional
    public UUID create(CreateHeroRequest createHeroRequest) {
        return heroRepository.create(new Hero(createHeroRequest, powerStatsService.create(
                new PowerStats(null, createHeroRequest.getStrength(),
                        createHeroRequest.getAgility(),
                        createHeroRequest.getDexterity(),
                        createHeroRequest.getIntelligence(), Instant.now(), Instant.now()))));
    }

    @Transactional
    public ResponseEntity<Object> findById(UUID id) {
        try {
            CreateHeroResponse createHeroResponse = heroRepository.findById(id);
            return ok(createHeroResponse);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Hero not found!", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<Object> findByName(String name) {
        try {
            CreateHeroResponse createHeroResponse = heroRepository.findByName(name);
            return ok().body(createHeroResponse);
        } catch (EmptyResultDataAccessException e) {
            return ok().build();
        }
    }

    @Transactional
    public ResponseEntity<Object> updateById(UUID id, UpdateHeroRequest updateHeroRequest) {
        try {
            heroRepository.updateHero(id, updateHeroRequest);
            return new ResponseEntity<>("Hero updated successfully", HttpStatus.OK);
        } catch (DuplicateKeyException e) {
            return new ResponseEntity<>("Duplicated Hero name", HttpStatus.BAD_REQUEST);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Hero not found", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public UUID getPowerStatsIdFromCurrentHero(UUID id) {
        return heroRepository.getPowerStatsIdFromCurrentHero(id);
    }

    @Transactional
    public ResponseEntity<Object> deleteHero(UUID id) {
        try {
            heroRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Hero not found", HttpStatus.NOT_FOUND);
        }
    }
}
