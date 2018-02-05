package pnodder.services;

import org.springframework.stereotype.Service;
import pnodder.model.Event;
import pnodder.repositories.EventRepository;

@Service
public class EventService {

    private EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Event findById(Integer id) {
        return repository.findById(id);
    }

    public void save(Event event) {
        repository.save(event);
    }

}
