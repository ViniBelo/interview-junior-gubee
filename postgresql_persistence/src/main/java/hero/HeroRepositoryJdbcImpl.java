package hero;


import data.builder.HeroDataBuilder;
import dto.HeroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import repositories.HeroRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HeroRepositoryJdbcImpl implements HeroRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String CREATE_HERO_QUERY = "INSERT INTO hero" +
            " (name, race, power_stats_id)" +
            " VALUES (:name, :race, :powerStatsId) RETURNING id";
    @Override
    public UUID create(HeroDTO hero) {
        final Map<String, Object> params = Map.of("name", hero.name(),
                "race", hero.race().name(),
                "powerStatsId", hero.powerStatsId()
        );
        return namedParameterJdbcTemplate.queryForObject(
                CREATE_HERO_QUERY,
                params,
                UUID.class
        );
    }

    private static final String GET_HERO_BY_ID_QUERY =
            "SELECT h.id, h.name, h.race, h.enabled, h.power_stats_id, ps.id, ps.strength, ps.agility, ps.dexterity, ps.intelligence " +
                    "FROM hero h " +
                    "JOIN power_stats ps " +
                    "ON h.power_stats_id = ps.id " +
                    "WHERE h.id = :id";
    @Override
    public HeroDataBuilder findById(UUID id) {
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
    @Override
    public HeroDataBuilder findByName(String name) {
        final Map<String, Object> params = Map.of("name", name);
        return namedParameterJdbcTemplate.queryForObject(
                GET_HERO_BY_NAME_QUERY,
                params,
                (resultSet, rowNumber) -> buildHero(resultSet)
        );
    }

    private static final String UPDATE_HERO_BY_ID_QUERY =
            "UPDATE hero " +
                    "SET " +
                    "name = :name, " +
                    "race = :race, " +
                    "updated_at = NOW() " +
                    "WHERE id = :id";
    @Override
    public void updateHero(UUID id, HeroDTO heroDTO) {
        final Map<String, Object> params = Map.of(
                "id", id,
                "name", heroDTO.name(),
                "race", heroDTO.race().name()
        );
        namedParameterJdbcTemplate.update(
                UPDATE_HERO_BY_ID_QUERY,
                params
        );
    }

    private HeroDataBuilder buildHero(ResultSet resultSet) throws SQLException {
        var id = UUID.fromString(resultSet.getString("id"));
        var name = resultSet.getString("name");
        var race = resultSet.getString("race");
        var powerStatsId = UUID.fromString(resultSet.getString("power_stats_id"));
        return HeroDataBuilder
                .builder()
                .id(id)
                .name(name)
                .race(race)
                .powerStatsId(powerStatsId)
                .build();
    }

    private static final String DELETE_HERO_QUERY =
            "DELETE " +
                    "FROM hero " +
                    "WHERE id = :id";
    @Override
    public void delete(UUID id) {
        final Map<String, Object> params = Map.of("id", id);
        namedParameterJdbcTemplate.update(
                DELETE_HERO_QUERY,
                params
        );
    }
}
