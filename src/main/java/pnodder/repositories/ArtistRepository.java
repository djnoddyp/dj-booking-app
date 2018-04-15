package pnodder.repositories;

import pnodder.model.Artist;

import java.util.List;

public interface ArtistRepository {
    List<Artist> findAll();

    Artist findById(Integer id);
}
