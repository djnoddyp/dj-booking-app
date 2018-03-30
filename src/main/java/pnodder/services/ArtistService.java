package pnodder.services;

import org.springframework.stereotype.Service;
import pnodder.mappers.ArtistMapper;
import pnodder.model.Artist;

import java.util.List;

@Service
public class ArtistService {
    
    private ArtistMapper artistMapper;

    public ArtistService(ArtistMapper artistMapper) {
        this.artistMapper = artistMapper;
    }

    public List<Artist> findAll() {
        return artistMapper.findAll();
    }

    public Artist findById(Integer id) {
        return artistMapper.findById(id);
    }
    

}
