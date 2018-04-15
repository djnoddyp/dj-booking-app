package pnodder.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pnodder.model.Artist;
import pnodder.model.Booking;
import pnodder.services.ArtistService;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingRepositoryImpl implements BookingRepository {

    private JdbcTemplate jdbcTemplate;
    private ArtistService artistService;

    private RowMapper<Booking> bookingRowMapper;

    public BookingRepositoryImpl(DataSource dataSource, ArtistService artistService) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.artistService = artistService;

        bookingRowMapper = (resultSet, rowNum) -> {
            Booking booking = new Booking();
            booking.setId(resultSet.getInt("ID"));
            booking.setName(resultSet.getString("Name"));
            booking.setDate(resultSet.getDate("Date").toLocalDate());
            booking.setStart(resultSet.getTime("StartTime").toLocalTime());
            booking.setFinish(resultSet.getTime("FinishTime").toLocalTime());
            booking.setArtists(resolveArtist(resultSet.getInt("ArtistID")));
            return booking;
        };
    }

    @Override
    public List<Booking> findAll() {
        return jdbcTemplate.query("SELECT * FROM Bookings", bookingRowMapper);
    }

    @Override
    public Booking findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM Bookings WHERE id = ?",
                new Object[]{id},
                bookingRowMapper);
    }

    @Override
    public List<Booking> findByName(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM Bookings WHERE Name = ?",
                new Object[]{name},
                bookingRowMapper);
    }

    @Override
    public void save(Booking booking) {
        for (Artist a : booking.getArtists()) {
            jdbcTemplate.update("INSERT INTO Bookings (Name, Date, StartTime, FinishTime, ArtistID) " +
                            "VALUES (?, ?, ?, ?, ?)", booking.getName(), booking.getDate(), booking.getStart(),
                    booking.getFinish(), a.getId());
        }
    }

    @Override
    public List<String> findDistinctName() {
        return jdbcTemplate.queryForList("SELECT DISTINCT Name FROM Bookings", String.class);
    }
    
    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("DELETE FROM Bookings WHERE Name = ?", name);
    }

    private Set<Artist> resolveArtist(int artistID) {
        Set<Artist> artists = new HashSet<>();
        Artist artist = artistService.findById(artistID);
        artists.add(artist);
        return artists;
    }
}
