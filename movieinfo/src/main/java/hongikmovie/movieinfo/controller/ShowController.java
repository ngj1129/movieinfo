package hongikmovie.movieinfo.controller;

import hongikmovie.movieinfo.domain.Movie;
import hongikmovie.movieinfo.domain.Show;
import hongikmovie.movieinfo.domain.Theater;
import hongikmovie.movieinfo.service.MovieService;
import hongikmovie.movieinfo.service.ShowService;
import hongikmovie.movieinfo.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShowController {

    private final MovieService movieService;

    private final TheaterService theaterService;

    private final ShowService showService;

    @GetMapping("/shows/new")
    public String createForm(Model model) {
        List<Movie> movies = movieService.findMovies();
        List<Theater> theaters = theaterService.findTheaters();

        model.addAttribute("movies", movies);
        model.addAttribute("theaters", theaters);

        return "shows/createShowForm";
    }

    @PostMapping("/shows/new")
    public String show(@RequestParam("movieId") Long movieId,
                       @RequestParam("theaterId") Long theaterId,
                       @RequestParam("showTime") LocalDateTime showTime) {

        showService.show(movieId, theaterId, showTime);
        return "redirect:/";
    }

    @GetMapping("/shows")
    public String shows(Model model) {
        List<Movie> movies = movieService.findMovies();
        List<Theater> theaters = theaterService.findTheaters();

        model.addAttribute("movies", movies);
        model.addAttribute("theaters", theaters);

        return "shows/chooseMovieandTheater";
    }

    @PostMapping("/shows")
    public String showList(@RequestParam("movieId") Long movieId,
                           @RequestParam("theaterId") Long theaterId,
                           Model model) {

        List<Show> shows = showService.findShows(movieId, theaterId);
        model.addAttribute("shows", shows);
        return "shows/showList";
    }

}
