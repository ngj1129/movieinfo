package hongikmovie.movieinfo.controller;

import hongikmovie.movieinfo.domain.Movie;
import hongikmovie.movieinfo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies/new")
    public String createForm(Model model) {
        model.addAttribute("form", new MovieForm());
        return "movies/createMovieForm";
    }

    @PostMapping("/movies/new")
    public String create(MovieForm form) {

        Movie movie = new Movie();
        movie.setName(form.getName());

        movieService.saveMovie(movie);
        return "redirect:/";
    }



}
