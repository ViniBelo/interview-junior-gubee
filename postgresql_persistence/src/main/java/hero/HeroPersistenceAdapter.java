package hero;

import application.port.out.*;
import data.builder.HeroDataBuilder;
import application.port.in.BuildHeroDto;
import dto.HeroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

@RequiredArgsConstructor
public class HeroPersistenceAdapter implements CreateHeroPort, FindHeroPort, DeleteHeroPort, UpdateHeroPort, FindStatsFromComparedHeroes {
    private final HeroRepositoryJdbcImpl heroRepositoryJdbcImpl;
    private final BuildHeroDto buildHeroDto;

    @Transactional
    @Override
    public UUID create(HeroDTO heroDTO) {
        return heroRepositoryJdbcImpl.create(heroDTO);
    }

    @Override
    public List<HeroDTO> findAll() {
        List<HeroDataBuilder> heroes = heroRepositoryJdbcImpl.findAll();
        List<HeroDTO> heroesDto = new ArrayList<>();
        for (HeroDataBuilder hero: heroes) {
            heroesDto.add(buildHero(hero));
        }
        return heroesDto;
    }

    @Transactional
    @Override
    public HeroDTO findById(UUID id) throws EmptyResultDataAccessException {
        return buildHero(heroRepositoryJdbcImpl.findById(id));
    }

    @Transactional
    @Override
    public HeroDTO findByName(String name) throws EmptyResultDataAccessException {
        return buildHero(heroRepositoryJdbcImpl.findByName(name));
    }

    private HeroDTO buildHero(HeroDataBuilder heroDataBuilder) {
        return buildHeroDto.createHeroDtoWithId(
                heroDataBuilder.getId(),
                heroDataBuilder.getName(),
                heroDataBuilder.getRace(),
                heroDataBuilder.getPowerStatsId()
        );
    }

    @Transactional
    @Override
    public void updateById(UUID id, HeroDTO heroDTO) throws DuplicateKeyException, EmptyResultDataAccessException {
        heroRepositoryJdbcImpl.updateHero(id, heroDTO);
    }

    @Transactional
    @Override
    public void deleteHero(UUID id) throws EmptyResultDataAccessException {
        heroRepositoryJdbcImpl.delete(id);
    }

    @Transactional
    @Override
    public Vector<HeroDTO> findUsersToCompare(UUID id1, UUID id2) {
        Vector<HeroDTO> heroes = new Vector<>();
        heroes.add(buildHero(heroRepositoryJdbcImpl.findById(id1)));
        heroes.add(buildHero(heroRepositoryJdbcImpl.findById(id2)));
        return heroes;
    }
}
