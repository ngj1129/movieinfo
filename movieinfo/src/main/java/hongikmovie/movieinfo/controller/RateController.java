package hongikmovie.movieinfo.controller;

import hongikmovie.movieinfo.domain.Member;
import hongikmovie.movieinfo.domain.Movie;
import hongikmovie.movieinfo.service.MemberService;
import hongikmovie.movieinfo.service.MovieService;
import hongikmovie.movieinfo.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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

    //평점정보- 영화목록
    @GetMapping("/ratings")
    public String createForm(@ModelAttribute("member") Member member, Model model) {
        if (member.getId() == null) {
            return "redirect:/";
        }

        List<Movie> movies = movieService.findMovies();

        model.addAttribute("member", member);
        model.addAttribute("movies", movies);

        return "ratings/ratingList";
    }

}
