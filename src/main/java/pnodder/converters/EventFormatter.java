package pnodder.converters;

import org.springframework.format.Formatter;
import pnodder.model.Event;
import pnodder.services.EventService;

import java.text.ParseException;
import java.util.Locale;

public class EventFormatter implements Formatter<Event> {

    private EventService eventService;

    public EventFormatter(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Event parse(String s, Locale locale) throws ParseException {
        return eventService.findById(Integer.valueOf(s));
    }

    @Override
    public String print(Event event, Locale locale) {
        return null;
    }
}
