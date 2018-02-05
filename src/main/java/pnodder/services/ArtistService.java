package pnodder.services;

import org.springframework.stereotype.Service;
import pnodder.model.Artist;
import pnodder.repositories.ArtistRepository;

import java.util.List;

@Service
public class ArtistService {
    
    private ArtistRepository repository;

    public ArtistService(ArtistRepository repository) {
        this.repository = repository;
    }

    public List<Artist> findAll() {
        return repository.findAll();
    }

    public Artist findById(Integer id) {
        return repository.findById(id);
    }
    

}
