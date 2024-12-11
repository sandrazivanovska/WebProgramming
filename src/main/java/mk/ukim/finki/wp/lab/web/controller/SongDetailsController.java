package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Review;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ReviewService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/songDetails")
public class SongDetailsController {
    private final SongService songService;
    private final ReviewService reviewService;

    public SongDetailsController(SongService songService, ReviewService reviewService) {
        this.songService = songService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getSongDetailsPage(HttpServletRequest req, Model model) {
        String trackId = (String) req.getSession().getAttribute("trackId");

        Song song = songService.findByTrackId(trackId);
        Artist selectedArtist = (Artist) req.getSession().getAttribute("selectedArtist");

        if (selectedArtist != null && !song.getPerformers().contains(selectedArtist)) {
            songService.addArtistToSong(selectedArtist, song);
        }

        List<Review> reviews = reviewService.findReviewsBySong(song);

        model.addAttribute("trackId", trackId);
        model.addAttribute("songTitle", song.getTitle());
        model.addAttribute("songGenre", song.getGenre());
        model.addAttribute("songRelease", song.getReleaseYear());
        model.addAttribute("songPerformers", song.getPerformers());
        model.addAttribute("reviews", reviews);  // Pass reviews to the view

        return "songDetails";
    }

    @PostMapping("/addReview")
    public String addReview(@ModelAttribute Review review, HttpServletRequest req) {
        String trackId = (String) req.getSession().getAttribute("trackId");
        Song song = songService.findByTrackId(trackId);
        review.setSong(song);

        reviewService.saveReview(review);

        return "redirect:/songDetails";
    }
}