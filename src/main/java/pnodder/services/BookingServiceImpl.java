package pnodder.services;

import org.springframework.stereotype.Service;
import pnodder.mappers.BookingMapper;
import pnodder.model.Artist;
import pnodder.model.Booking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingMapper bookingMapper;

    public BookingServiceImpl(BookingMapper bookingMapper) {
        this.bookingMapper = bookingMapper;
    }

    @Override
    public Booking findById(Integer id) {
        return bookingMapper.findById(id);
    }
    
    @Override
    public List<Booking> findAll() {
        return bookingMapper.findAll();
    }

    @Override
    public List<Booking> findAllDistinct() {
        List<String> names = bookingMapper.findDistinctName();
        List<Booking> bookings = new ArrayList<>();
        names.forEach(name -> bookings.add(findBookingByName(name)));
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

    @Override
    public void save(Booking booking) {
        for(Artist a : booking.getArtists()) {
            bookingMapper.save(booking, a.getId());
        }
    }

    @Override
    public Booking findBookingByName(String name) {
        List<Booking> bookings = bookingMapper.findByName(name);
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

    @Override
    public void deleteByName(String name) {
        bookingMapper.deleteByName(name);
    }
    
    @Override
    public void updateByName(String name, Booking booking) {
        deleteByName(name);
        save(booking);
    }
    
}
