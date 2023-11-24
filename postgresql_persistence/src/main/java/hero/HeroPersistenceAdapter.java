package hero;

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

    private final HeroRepository heroRepository;

    @Transactional
    @Override
    public UUID create(HeroDTO heroDTO) {
        return heroRepository.create(heroDTO);
    }

    @Transactional
    @Override
    public HeroDTO findById(UUID id) throws EmptyResultDataAccessException {
        return heroRepository.findById(id);
    }

    @Transactional
    @Override
    public HeroDTO findByName(String name) throws EmptyResultDataAccessException {
        return heroRepository.findByName(name);
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
