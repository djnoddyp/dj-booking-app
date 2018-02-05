package pnodder.services;

import org.springframework.stereotype.Service;
import pnodder.model.Bike;
import pnodder.repositories.BikeRepository;

import java.util.List;

@Service
public class BikeService {
    
    private BikeRepository repository;

    public BikeService(BikeRepository repository) {
        this.repository = repository;
    }

    public List<Bike> findAll() {
        return repository.findAll();
    }
    
    public Bike findById(Integer id) {
        return repository.findById(id);
    }
    

}
