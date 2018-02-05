package pnodder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pnodder.converters.ArtistFormatter;
import pnodder.converters.EventFormatter;
import pnodder.model.Event;
import pnodder.services.ArtistService;
import pnodder.services.EventService;

@Controller
public class EventController {

    private ArtistService artistService;
    private EventService eventService;

    public EventController(ArtistService artistService, EventService eventService) {
        this.artistService = artistService;
        this.eventService = eventService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new ArtistFormatter(artistService));
        binder.addCustomFormatter(new EventFormatter(eventService));
    }

    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("allArtists", artistService.findAll());
        model.addAttribute("event", new Event());
    }

//    @GetMapping("/home")
//    public String getHome() {
//        return "home";
//    }
//
//    @GetMapping("/bikes")
//    public String getBikes() {
//        return "bikes";
//    }

    @GetMapping("/event")
    public String getOrder() {
        return "event";
    }

    @PostMapping("/submitevent")
    public String submitOrder(Event event, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            eventService.save(event);
            return "redirect:/event";
        } else {
            return "event";
        }
    }

//    @PostMapping("/saveOrder")
//    public String saveResident(Order order) {
//        orderRepository.save(order);
//        return "index";
//    }
    
    
}
