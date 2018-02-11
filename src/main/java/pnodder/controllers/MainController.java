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
    public void initialiseModel(Model model) {
        model.addAttribute("bookingId", new Integer(0));
    }

    @GetMapping("/booking")
    public String getBookingForm(Model model) {
        model.addAttribute("allArtists", artistService.findAll());
        model.addAttribute("booking", new Booking());
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
    
    @GetMapping("/mybookings")
    public String getMyBookings(Model model) {
        model.addAttribute("allBookings", bookingService.findAllDistinct());
        return "bookings";
    }

    @PostMapping("/editbooking")
    public String editBooking(@RequestParam("bookingId") Integer id) {
        System.out.println("ID received: " + id);
        return "redirect:/booking";
    }

    @PostMapping("/deletebooking")
    public String deleteBooking(@RequestParam("bookingId") Integer id) {
        bookingService.deleteById(id);
        return "redirect:/mybookings";
    }

}
