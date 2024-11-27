package mk.ukim.finki.wp.lab.model.exceptions;

public class SongNotFound extends RuntimeException {
    public SongNotFound(Long albumId) {
        super(String.format("Album with ID %d not found.", albumId));
    }
}