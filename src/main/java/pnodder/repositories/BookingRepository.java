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

    private ArtistService getArtistService() {
        return artistService;
    }

    private final class BookingMapper implements RowMapper<Booking> {
        @Override
        public Booking mapRow(ResultSet resultSet, int i) throws SQLException {
            Booking booking = new Booking();
            booking.setId(resultSet.getInt("ID"));
            booking.setName(resultSet.getString("Name"));
            booking.setDate(resultSet.getDate("Date").toLocalDate());
            booking.setStartTime(resultSet.getTime("StartTime").toLocalTime());
            booking.setFinishTime(resultSet.getTime("FinishTime").toLocalTime());
            booking.setArtists(resolveArtist(resultSet.getInt("ArtistID")));
            return booking;
        }

        private Set<Artist> resolveArtist(int artistID) {
            Set<Artist> artists = new HashSet<>();
            Artist artist = BookingRepository.this.getArtistService().findById(artistID);
            artists.add(artist);
            return artists;
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

    public List<String> findDistinctName() {
        return jdbcTemplate.queryForList("SELECT DISTINCT Name FROM Bookings", String.class);
    }

    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM Bookings WHERE ID = ?", id);
    }

}
