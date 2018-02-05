package pnodder.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pnodder.model.Bike;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BikeRepository {
    
    private JdbcTemplate jdbcTemplate;

    public BikeRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    private static final class BikeMapper implements RowMapper<Bike> {
        @Override
        public Bike mapRow(ResultSet resultSet, int i) throws SQLException {
            Bike bike = new Bike();
            bike.setId(resultSet.getInt("id"));
            bike.setMake(resultSet.getString("make"));
            bike.setModel(resultSet.getString("model"));
            bike.setPrice(resultSet.getInt("price"));
            return bike;
        }
    }

    public List<Bike> findAll() {
        return jdbcTemplate.query("SELECT * FROM bike", new BikeMapper());
    }
    
    public Bike findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM bike WHERE id = ?",
                new Object[]{id},
                new BikeMapper());
    }
    
}
