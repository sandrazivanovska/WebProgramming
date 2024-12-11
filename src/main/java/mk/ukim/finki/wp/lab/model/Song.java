package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import mk.ukim.finki.wp.lab.model.Review;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String trackId;

    private String title;

    private String genre;

    private int releaseYear;

    @ManyToMany
    @JoinTable(
            name = "song_performers",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    List<Artist> performers;

    @ManyToOne
    private Album album;

    @OneToMany
    private List<Review> reviews;


    public Song() {
    }

    public Song(String trackId, String title, String genre, int releaseYear, List<Artist> performers, Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = performers;
        this.album = album;
    }

}