package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Song song;

    public Review() {
    }

    public Review(String content) {
        this.content = content;
    }
}
