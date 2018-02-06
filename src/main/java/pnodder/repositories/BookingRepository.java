package pnodder.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pnodder.model.Artist;
import pnodder.model.Booking;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Repository
public class BookingRepository {

    private JdbcTemplate jdbcTemplate;

    public BookingRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class BookingMapper implements RowMapper<Booking> {
        @Override
        public Booking mapRow(ResultSet resultSet, int i) throws SQLException {
            Booking booking = new Booking();
            booking.setId(resultSet.getInt("ID"));
            booking.setName(resultSet.getString("Name"));
            booking.setDate(resultSet.getDate("Date").toLocalDate());
            booking.setStartTime(resultSet.getTime("StartTime").toLocalTime());
            booking.setFinishTime(resultSet.getTime("FinishTime").toLocalTime());
            booking.setArtists(resultSet.getObject("Artists", Set.class));
            return booking;
        }
    }

    public List<Booking> findAll() {
        return jdbcTemplate.query("SELECT * FROM Bookings", new BookingMapper());
    }

    public Booking findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM Bookings WHERE id = ?",
                new Object[]{id},
                new BookingMapper());
    }

    public void save(Booking booking) {
        for (Artist a : booking.getArtists()) {
            jdbcTemplate.update("INSERT INTO Bookings (Name, Date, StartTime, FinishTime, ArtistID) " +
                            "VALUES (?, ?, ?, ?, ?)", booking.getName(), booking.getDate(), booking.getStartTime(),
                    booking.getFinishTime(), a.getId());
        }
    }

}
