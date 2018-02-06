package pnodder.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pnodder.model.Artist;
import pnodder.model.Booking;
import pnodder.services.ArtistService;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class BookingRepository {

    private JdbcTemplate jdbcTemplate;
    private ArtistService artistService;

    public BookingRepository(DataSource dataSource, ArtistService artistService) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.artistService = artistService;
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
            //booking.setArtists(resultSet.getObject("Artists", Set.class));
            return booking;
        }

//        private Set<Artist> resolveArtists() {
//            Set<Artist> artists = new HashSet<>();
//            for (Booking b : )
//        }
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

    public List<Booking> findByName(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM Bookings WHERE Name = ?",
                new Object[]{name},
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
