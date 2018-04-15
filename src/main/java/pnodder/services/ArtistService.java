package pnodder.services;

import pnodder.model.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> findAll();

    Artist findById(Integer id);
}
