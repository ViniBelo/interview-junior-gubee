package hero;

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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@RequiredArgsConstructor
public class HeroPersistenceAdapter implements CreateHeroPort, FindHeroPort, DeleteHeroPort, UpdateHeroPort {
    private final HeroRepository heroRepository;
    private final BuildHeroDto buildHeroDto;

    @Transactional
    @Override
    public UUID create(HeroDTO heroDTO) {
        System.out.println("HPA");
        return heroRepository.create(heroDTO);
    }

    @Transactional
    @Override
    public HeroDTO findById(UUID id) throws EmptyResultDataAccessException {
        ResultSet resultSet = heroRepository.findById(id);
        return buildHero(resultSet);
    }

    @Transactional
    @Override
    public HeroDTO findByName(String name) throws EmptyResultDataAccessException {
        ResultSet resultSet = heroRepository.findByName(name);
        return buildHero(resultSet);
    }

    private HeroDTO buildHero(ResultSet resultSet) {
        try {
            var name = resultSet.getString("name");
            var race = resultSet.getString("race");
            var powerStatsId = UUID.fromString(resultSet.getString("power"));
            return buildHeroDto.createHeroDto(name, race, powerStatsId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public void updateById(UUID id, HeroDTO heroDTO) throws DuplicateKeyException, EmptyResultDataAccessException {
        heroRepository.updateHero(id, heroDTO);
    }

    @Transactional
    @Override
    public void deleteHero(UUID id) throws EmptyResultDataAccessException {
        heroRepository.delete(id);
    }
}
