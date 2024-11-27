package mk.ukim.finki.wp.lab.model.exceptions;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(Long albumId) {
        super(String.format("Album with ID %d not found.", albumId));
    }
}