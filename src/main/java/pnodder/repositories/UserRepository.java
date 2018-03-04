package pnodder.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    final String sql = "SELECT users.username, user_roles.role_name, roles_permissions.permission FROM users " +
            "INNER JOIN user_roles ON users.username=user_roles.username INNER JOIN roles_permissions ON " +
            "user_roles.role_name=roles_permissions.role_name";


    public List findAllWithRolesPermissions() {
        return jdbcTemplate.queryForList(sql);
    }

}
