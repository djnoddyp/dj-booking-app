package pnodder.mappers;

import org.springframework.stereotype.Component;
import pnodder.model.Artist;

import java.util.List;

@Component
public interface ArtistMapper {

    List<Artist> findAll();

    Artist findById(Integer id);

}
