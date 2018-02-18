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
        // sort bookings by date, soonest first
        bookings.sort((b1, b2) -> {
            if (b1.getDate().isAfter(b2.getDate())) {
                return 1;
            } else if (b1.getDate().isBefore(b2.getDate())) {
                return -1;
            } else {
                return 0;
            }
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
        Double cost = 0d;
        for (Booking b : bookings) {
            artistSet.add(b.getArtists().iterator().next());
            cost += b.getArtists().iterator().next().getFee();
            if (booking.getName() == null) {
                booking.setId(b.getId());
                booking.setName(b.getName());
                booking.setDate(b.getDate());
                booking.setStart(b.getStart());
                booking.setFinish(b.getFinish());
            }
        }
        booking.setArtists(artistSet);
        booking.setCost(cost);
        return booking;
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
    }
    
    public void updateByName(String name, Booking booking) {
        deleteByName(name);
        save(booking);
    }
    
}
