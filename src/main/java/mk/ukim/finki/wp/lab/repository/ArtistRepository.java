package mk.ukim.finki.wp.lab.repository;
import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {
    List<Artist> artistList = new ArrayList<>();

    public ArtistRepository() {
        artistList.add(new Artist(1L, "Freddie", "Mercury", "legendary singer and performer"));
        artistList.add(new Artist(2L, "Elvis", "Presley", "king of rock and roll"));
        artistList.add(new Artist(3L, "Whitney", "Houston", "iconic pop and soul singer"));
        artistList.add(new Artist(4L, "Michael", "Jackson", "king of pop"));
        artistList.add(new Artist(5L, "Beyoncé", "Knowles", "singer, dancer, and actress"));
    }

    public List<Artist> findAll() {
        return artistList;
    }

    public Optional<Artist> findById(Long id) {
        return artistList.stream().filter(x -> x.getId().equals(id)).findFirst();
    }
}
