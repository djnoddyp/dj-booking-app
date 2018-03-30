package pnodder.mappers;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    List findAllWithRolesPermissions();

}
