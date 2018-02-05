package pnodder.formatters;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import pnodder.model.Bike;
import pnodder.services.BikeService;

import java.text.ParseException;
import java.util.Locale;

public class BikeFormatter implements Formatter<Bike> {

    private BikeService bikeService;

    public BikeFormatter(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @Override
    public Bike parse(String text, Locale locale) throws ParseException {
        return bikeService.findById(Integer.valueOf(text));
    }

    @Override
    public String print(Bike object, Locale locale) {
        return null;
    }
}
