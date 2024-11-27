package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import java.io.IOException;


@WebServlet(name = "SongDetailsServlet", urlPatterns = "/songDetails/servlet")
public class SongDetailsServlet extends HttpServlet {

    private final SongService songService;
    private final SpringTemplateEngine springTemplateEngine;

    public SongDetailsServlet(SongService songService, SpringTemplateEngine springTemplateEngine) {
        this.songService = songService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String trackId = (String) req.getSession().getAttribute("trackId");
        Song song = songService.findByTrackId(trackId);
        Artist selectedArtist = (Artist) req.getSession().getAttribute("selectedArtist");
        if(!song.getPerformers().contains(selectedArtist)) {
            songService.addArtistToSong(selectedArtist, song);
        }

        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);

        webContext.setVariable("song", song);
        webContext.setVariable("performers", song.getPerformers());

        this.springTemplateEngine.process("songDetails.html", webContext, resp.getWriter());
    }

}
