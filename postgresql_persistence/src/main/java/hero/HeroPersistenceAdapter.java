package hero;

import data.builder.HeroDataBuilder;
import application.port.in.BuildHeroDto;
import application.port.out.CreateHeroPort;
import application.port.out.DeleteHeroPort;
import application.port.out.FindHeroPort;
import application.port.out.UpdateHeroPort;
import dto.HeroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class HeroPersistenceAdapter implements CreateHeroPort, FindHeroPort, DeleteHeroPort, UpdateHeroPort {
    private final HeroRepositoryJdbcImpl heroRepositoryJdbcImpl;
    private final BuildHeroDto buildHeroDto;

    @Transactional
    @Override
    public UUID create(HeroDTO heroDTO) {
        return heroRepositoryJdbcImpl.create(heroDTO);
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
        return buildHeroDto.createHeroDto(
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
}
