package pnodder.services;

import org.springframework.stereotype.Service;
import pnodder.model.Artist;
import pnodder.model.Booking;
import pnodder.repositories.BookingRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<Booking> findAllDistinct() {
        List<String> names = repository.findDistinctName();
        List<Booking> bookings = new ArrayList<>();
        names.forEach(name -> {
            bookings.add(findBookingByName(name));
        });
        return bookings;
    }

    public void save(Booking booking) {
        repository.save(booking);
    }

    public Booking findBookingByName(String name) {
        List<Booking> bookings = repository.findByName(name);
        Booking booking = new Booking();
        Set<Artist> artistSet = new HashSet<>();
        for (Booking b : bookings) {
            artistSet.add(b.getArtists().iterator().next());
            if (booking.getName() == null) {
                booking.setId(b.getId());
                booking.setName(b.getName());
                booking.setDate(b.getDate());
                booking.setStartTime(b.getStartTime());
                booking.setFinishTime(b.getFinishTime());
            }
        }
        booking.setArtists(artistSet);
        return booking;
    }

    public void deleteById(Integer id) {

        repository.deleteById(id);
    }

}
