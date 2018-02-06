package pnodder.services;

import org.springframework.stereotype.Service;
import pnodder.model.Booking;
import pnodder.repositories.BookingRepository;

import java.util.List;

@Service
public class BookingService {

    private BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public Booking findById(Integer id) {
        return repository.findById(id);
    }
    
    public List<Booking> findAll() {
        return repository.findAll();
    }

    public void save(Booking booking) {
        repository.save(booking);
    }

}
