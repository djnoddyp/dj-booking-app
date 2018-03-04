package pnodder.services;

import pnodder.model.Booking;

import java.util.List;

public interface BookingService {
    Booking findById(Integer id);

    List<Booking> findAll();

    List<Booking> findAllDistinct();

    void save(Booking booking);

    Booking findBookingByName(String name);

    void deleteByName(String name);

    void updateByName(String name, Booking booking);
}
