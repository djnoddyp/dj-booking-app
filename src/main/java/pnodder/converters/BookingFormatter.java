package pnodder.converters;

import org.springframework.format.Formatter;
import pnodder.model.Booking;
import pnodder.services.BookingService;

import java.text.ParseException;
import java.util.Locale;

public class BookingFormatter implements Formatter<Booking> {

    private BookingService bookingService;

    public BookingFormatter(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public Booking parse(String s, Locale locale) throws ParseException {
        return bookingService.findById(Integer.valueOf(s));
    }

    @Override
    public String print(Booking booking, Locale locale) {
        return null;
    }
}
