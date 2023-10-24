package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HeroRepository {

    private static final String CREATE_HERO_QUERY = "INSERT INTO hero" +
        " (name, race, power_stats_id)" +
        " VALUES (:name, :race, :powerStatsId) RETURNING id";

    private static final String GET_HERO_BY_ID_QUERY = "SELECT * FROM" +
            " hero h WHERE h.id = :id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    UUID create(Hero hero) {
        final Map<String, Object> params = Map.of("name", hero.getName(),
            "race", hero.getRace().name(),
            "powerStatsId", hero.getPowerStatsId());

        return namedParameterJdbcTemplate.queryForObject(
            CREATE_HERO_QUERY,
            params,
            UUID.class);
    }

    Hero findById(UUID id) {
        final Map<String, Object> params = Map.of("id", id);
        return namedParameterJdbcTemplate.queryForObject(GET_HERO_BY_ID_QUERY,
                params,
                (resultSet, rowNumber) -> {
                    Hero hero = new Hero(new CreateHeroRequest(
                            null,
                            null,
                            0,
                            0,
                            0,
                            0),
                            id);
            hero.setId(resultSet.getObject("id", UUID.class));
            hero.setName(resultSet.getString("name"));
            hero.setRace(Race.valueOf(resultSet.getString("race")));
            hero.setPowerStatsId(resultSet.getObject("power_stats_id", UUID.class));
            hero.setEnabled(resultSet.getBoolean("enabled"));
            return hero;
                }
                );
    }
}
