package pnodder.services;

import pnodder.model.Artist;
import pnodder.repositories.ArtistRepository;

import java.util.List;

public class ArtistServiceImpl implements ArtistService {
    
    private ArtistRepository repository;

    public ArtistServiceImpl(ArtistRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Artist> findAll() {
        return repository.findAll();
    }

    @Override
    public Artist findById(Integer id) {
        return repository.findById(id);
    }
    

}
