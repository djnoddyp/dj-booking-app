package pnodder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pnodder.converters.ArtistFormatter;
import pnodder.converters.BookingFormatter;
import pnodder.model.Booking;
import pnodder.services.ArtistService;
import pnodder.services.BookingService;

@Controller
public class MainController {

    private ArtistService artistService;
    private BookingService bookingService;

    public MainController(ArtistService artistService, BookingService bookingService) {
        this.artistService = artistService;
        this.bookingService = bookingService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new ArtistFormatter(artistService));
        binder.addCustomFormatter(new BookingFormatter(bookingService));
    }

    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("allArtists", artistService.findAll());
        model.addAttribute("booking", new Booking());
        //model.addAttribute("allBookings", bookingService.findAll());
    }

    @GetMapping("/booking")
    public String getBookingForm() {
        return "booking";
    }

    @PostMapping("/submitbooking")
    public String submitBooking(Booking booking, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            bookingService.save(booking);
            return "redirect:/booking";
        } else {
            return "booking";
        }
    }
    
    @GetMapping
    public String getAllBookings() {
        return "bookings";
    }

    @GetMapping("/bookings/{name}")
    public String getBookingsByName(@PathVariable String name, Model model) {
        model.addAttribute("namedBooking", bookingService.findBookingByname(name));
        return "bookings";
    }

}
