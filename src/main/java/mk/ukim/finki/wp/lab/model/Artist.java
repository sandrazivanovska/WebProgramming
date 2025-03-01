package mk.ukim.finki.wp.lab.model;
//add comment
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private  String lastName;

    private String bio;


    @ManyToMany(mappedBy = "performers")
    private List<Song> songs = new ArrayList<>();

    public Artist() {
    }

    public Artist(String firstName, String lastName, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }


}
