package repositories;

import data.builder.HeroDataBuilder;
import dto.HeroDTO;

import java.util.List;
import java.util.UUID;

public interface HeroRepository {
    public UUID create(HeroDTO hero);
    public List<HeroDataBuilder> findAll();
    public HeroDataBuilder findById(UUID id);
    public HeroDataBuilder findByName(String name);
    public void updateHero(UUID id, HeroDTO heroDTO);
    public void delete(UUID id);
}
