package pnodder.converters;

import org.springframework.format.Formatter;
import pnodder.model.Artist;
import pnodder.services.ArtistService;

import java.text.ParseException;
import java.util.Locale;

public class ArtistFormatter implements Formatter<Artist> {

    private ArtistService artistService;

    public ArtistFormatter(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Override
    public Artist parse(String text, Locale locale) throws ParseException {
        return artistService.findById(Integer.valueOf(text));
    }

    @Override
    public String print(Artist object, Locale locale) {
        return null;
    }
}
