package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Review;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;

public interface ReviewService {
    // Save a review
    Review saveReview(Review review);

    // Find all reviews for a specific song
    List<Review> findReviewsBySong(Song song);
}
