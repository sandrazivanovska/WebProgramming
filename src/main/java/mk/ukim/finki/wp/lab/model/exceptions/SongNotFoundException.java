package mk.ukim.finki.wp.lab.model.exceptions;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(Long albumId) {
        super(String.format("Album with ID %d not found.", albumId));
    }
}