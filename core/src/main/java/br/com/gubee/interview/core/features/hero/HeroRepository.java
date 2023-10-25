package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import br.com.gubee.interview.model.response.CreateHeroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HeroRepository {

    private static final String CREATE_HERO_QUERY = "INSERT INTO hero" +
        " (name, race, power_stats_id)" +
        " VALUES (:name, :race, :powerStatsId) RETURNING id";


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public UUID create(Hero hero) {
        final Map<String, Object> params = Map.of("name", hero.getName(),
            "race", hero.getRace().name(),
            "powerStatsId", hero.getPowerStatsId());

        return namedParameterJdbcTemplate.queryForObject(
            CREATE_HERO_QUERY,
            params,
            UUID.class);
    }

    private static final String GET_HERO_BY_ID_QUERY =
            "SELECT h.id, h.name, h.race, h.enabled, h.power_stats_id, ps.id, ps.strength, ps.agility, ps.dexterity, ps.intelligence " +
                    "FROM hero h " +
                    "JOIN power_stats ps " +
                    "ON h.power_stats_id = ps.id " +
                    "WHERE h.id = :id";

    public CreateHeroResponse findById(UUID id) {
        final Map<String, Object> params = Map.of("id", id);
        return namedParameterJdbcTemplate.queryForObject(
                GET_HERO_BY_ID_QUERY,
                params,
                (resultSet, rowNumber) -> buildHero(resultSet)
        );
    }

    private static final String GET_HERO_BY_NAME_QUERY =
            "SELECT h.id, h.name, h.race, h.enabled, h.power_stats_id, ps.id, ps.strength, ps.agility, ps.dexterity, ps.intelligence " +
                    "FROM hero h " +
                    "JOIN power_stats ps " +
                    "ON h.power_stats_id = ps.id " +
                    "WHERE h.name " +
                    "ILIKE :name";
    public CreateHeroResponse findByName(String name) {
        final Map<String, Object> params = Map.of("name", name);
        return namedParameterJdbcTemplate.queryForObject(
                GET_HERO_BY_NAME_QUERY,
                params,
                (resultSet, rowNumber) -> buildHero(resultSet)
        );
    }

    private CreateHeroResponse buildHero(ResultSet resultSet) throws SQLException {
        var name = resultSet.getString("name");
        var race = Race.valueOf(resultSet.getString("race"));
        var strength = resultSet.getInt("strength");
        var agility = resultSet.getInt("agility");
        var dexterity = resultSet.getInt("dexterity");
        var intelligence = resultSet.getInt("intelligence");
        return CreateHeroResponse
                .builder()
                .name(name)
                .race(race)
                .strength(strength)
                .agility(agility)
                .dexterity(dexterity)
                .intelligence(intelligence)
                .build();
    }

    private static final String UPDATE_HERO_BY_ID_QUERY =
            "UPDATE hero " +
            "SET " +
                    "name = :name, " +
                    "race = :race, " +
                    "updated_at = NOW() " +
            "WHERE id = :id";

    public void updateHero(UUID id, UpdateHeroRequest updateHeroRequest) {
        final Map<String, Object> params = Map.of(
                "id", id,
                "name", updateHeroRequest.getName(),
                "race", updateHeroRequest.getRace().name()
        );
        namedParameterJdbcTemplate.update(
                UPDATE_HERO_BY_ID_QUERY,
                params
        );
    }

    private static final String GET_POWER_STATS_ID_FROM_CURRENT_HERO_QUERY =
            "SELECT h.power_stats_id " +
                    "FROM hero h " +
                    "WHERE id = :id";

    public UUID getPowerStatsIdFromCurrentHero(UUID id) {
        final Map<String, Object> params = Map.of("id", id);
        return namedParameterJdbcTemplate.queryForObject(
                GET_POWER_STATS_ID_FROM_CURRENT_HERO_QUERY,
                params,
                UUID.class);
    }
}
