package pnodder.repositories;

import pnodder.model.Booking;

import java.util.List;

public interface BookingRepository {
    List<Booking> findAll();

    Booking findById(Integer id);

    List<Booking> findByName(String name);

    void save(Booking booking);

    List<String> findDistinctName();

    void deleteByName(String name);
}
