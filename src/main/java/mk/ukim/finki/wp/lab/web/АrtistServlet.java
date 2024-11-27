package mk.ukim.finki.wp.lab.web;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "artist", urlPatterns = "/servlet/artist")
public class АrtistServlet extends HttpServlet {

    private final ArtistService artistService;
    private final SpringTemplateEngine springTemplateEngine;
    public АrtistServlet(ArtistService artistService, SpringTemplateEngine springTemplateEngine) {
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext webContext=new WebContext(webExchange);

        webContext.setVariable("artists",artistService.listArtists());
        String trackId=(String) req.getSession().getAttribute("trackId");
        webContext.setVariable("trackId",trackId);

        this.springTemplateEngine.process("artistsList.html",webContext,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idArtist = req.getParameter("id");
        Artist selectedArtist = artistService.findById(Long.valueOf(idArtist));
        req.getSession().setAttribute("selectedArtist",selectedArtist);
        resp.sendRedirect("/songDetails");
    }


}
