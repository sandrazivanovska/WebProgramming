package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepository {
    private List<Album> albums;

    public AlbumRepository() {
        albums = new ArrayList<>();

        albums.add(new Album("Nevermind", "Grunge", "1991"));
        albums.add(new Album("Rumours", "Rock", "1977"));
        albums.add(new Album("1989", "Pop", "2014"));
        albums.add(new Album("Kind of Blue", "Jazz", "1959"));
        albums.add(new Album("Random Access Memories", "Electronic", "2013"));
    }

    public List<Album> findAll(){
        return albums;
    }

    public Optional<Album> findById(Long id){
        return albums.stream()
                .filter(album -> album.getId().equals(id))
                .findFirst();
    }

}
