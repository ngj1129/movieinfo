package hongikmovie.movieinfo.controller;

import hongikmovie.movieinfo.domain.Movie;
import hongikmovie.movieinfo.domain.Theater;
import hongikmovie.movieinfo.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;

    @GetMapping("/theaters/new")
    public String createForm(Model model) {
        model.addAttribute("form", new TheaterForm());
        return "theaters/createTheaterForm";
    }

    @PostMapping("/theaters/new")
    public String create(TheaterForm form) {

        Theater theater = new Theater();
        theater.setName(form.getName());

        theaterService.saveTheater(theater);
        return "redirect:/";
    }

}
