package pnodder.services;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import pnodder.model.Artist;
import pnodder.model.Booking;
import pnodder.repositories.BookingRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingServiceImpl implements BookingService {

    private BookingRepository repository;
    private TransactionTemplate transactionTemplate;

    public BookingServiceImpl(BookingRepository repository, DataSourceTransactionManager transactionManager) {
        this.repository = repository;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Override
    public Booking findById(Integer id) {
        return repository.findById(id);
    }
    
    @Override
    public List<Booking> findAll() {
        return repository.findAll();
    }

    @Override
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

    @Override
    public void save(Booking booking) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                repository.save(booking);
            }
        });
    }

    @Override
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

    @Override
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }
    
    @Override
    public void updateByName(String name, Booking booking) {
        deleteByName(name);
        save(booking);
    }
    
}
