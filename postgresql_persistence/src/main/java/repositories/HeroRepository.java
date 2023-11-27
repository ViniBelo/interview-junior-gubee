package repositories;

import data.builder.HeroDataBuilder;
import dto.HeroDTO;

import java.util.UUID;

public interface HeroRepository {
    public UUID create(HeroDTO hero);
    public HeroDataBuilder findById(UUID id);
    public HeroDataBuilder findByName(String name);
    public void updateHero(UUID id, HeroDTO heroDTO);
    public void delete(UUID id);
}
