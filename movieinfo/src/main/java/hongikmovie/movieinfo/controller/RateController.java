package hongikmovie.movieinfo.controller;

import hongikmovie.movieinfo.domain.Member;
import hongikmovie.movieinfo.domain.Movie;
import hongikmovie.movieinfo.domain.Rate;
import hongikmovie.movieinfo.service.MemberService;
import hongikmovie.movieinfo.service.MovieService;
import hongikmovie.movieinfo.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("member")
public class RateController {

    private final RateService rateService;

    private final MovieService movieService;

    private final MemberService memberService;


    //맞는지모르겠음
    @ModelAttribute("member")
    public Member setMember() {
        return new Member();
    }

    @GetMapping("/rate")
    public String doRate(@ModelAttribute("member") Member member, Model model) {
        if (member.getId() == null) {
            return "redirect:/";
        }

        List<Movie> movies = movieService.findMovies();

        model.addAttribute("member", member);
        model.addAttribute("movies", movies);

        return "ratings/doRateForm";
    }

    @PostMapping("/rate")
    public String rate(@ModelAttribute("member") Member member,
                       @RequestParam("movieId") Long movieId,
                       @RequestParam("score") float score) {

        rateService.rate(member.getId(), movieId, score);
        return "redirect:/ratings";
    }

    @GetMapping("/ratings")
    public String ratingList(@ModelAttribute("member") Member member, Model model) {

        List<Rate> ratings = rateService.findRatings(member);
        model.addAttribute("ratings", ratings);

        return "ratings/ratingList";
    }


}
