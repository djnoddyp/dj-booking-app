package pnodder.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pnodder.model.Artist;
import pnodder.model.Event;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Repository
public class EventRepository {

    private JdbcTemplate jdbcTemplate;

    public EventRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class EventMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet resultSet, int i) throws SQLException {
            Event event = new Event();
            event.setId(resultSet.getInt("ID"));
            event.setName(resultSet.getString("Name"));
            event.setDate(resultSet.getDate("Date").toLocalDate());
            event.setStartTime(resultSet.getTime("StartTime").toLocalTime());
            event.setFinishTime(resultSet.getTime("FinishTime").toLocalTime());
            event.setArtists(resultSet.getObject("Artists", Set.class));
            return event;
        }
    }

    public List<Event> findAll() {
        return jdbcTemplate.query("SELECT * FROM Events", new EventMapper());
    }

    public Event findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM Events WHERE id = ?",
                new Object[]{id},
                new EventMapper());
    }

    public void save(Event event) {
        for (Artist a : event.getArtists()) {
            jdbcTemplate.update("INSERT INTO Events (Name, Date, StartTime, FinishTime, ArtistID) " +
                            "VALUES (?, ?, ?, ?, ?)", event.getName(), event.getDate(), event.getStartTime(),
                    event.getFinishTime(), a.getId());
        }
    }

}
