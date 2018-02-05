package pnodder.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pnodder.model.Artist;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArtistRepository {

    private JdbcTemplate jdbcTemplate;

    public ArtistRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class ArtistMapper implements RowMapper<Artist> {
        @Override
        public Artist mapRow(ResultSet resultSet, int i) throws SQLException {
            Artist artist = new Artist();
            artist.setId(resultSet.getInt("ID"));
            artist.setName(resultSet.getString("Name"));
            artist.setFee(resultSet.getDouble("Fee"));
            return artist;
        }
    }

    public List<Artist> findAll() {
        return jdbcTemplate.query("SELECT * FROM Artists", new ArtistMapper());
    }

    public Artist findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM Artists WHERE id = ?",
                new Object[]{id},
                new ArtistMapper());
    }

}
