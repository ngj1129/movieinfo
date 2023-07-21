package hongikmovie.movieinfo.service;

import hongikmovie.movieinfo.domain.Movie;
import hongikmovie.movieinfo.domain.Show;
import hongikmovie.movieinfo.domain.Theater;
import hongikmovie.movieinfo.repository.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@Transactional
public class AllTest {

    private static final Logger logger = LoggerFactory.getLogger(AllTest.class);

    @Autowired EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Autowired MemberService memberService;

    @Autowired
    MovieRepository movieRepository;

    @Autowired MovieService movieService;

    @Autowired
    RateRepository rateRepository;

    @Autowired RateService rateService;

    @Autowired
    ShowRepository showRepository;

    @Autowired ShowService showService;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired TheaterService theaterService;

    @Test
    @Transactional
    @Rollback(value = false)
    @Commit
    public void 영화_상영관_상영정보_생성() {

        //영화 생성//
        Movie movie1 = new Movie();
        movie1.setName("영화1");
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setName("영화2");
        movieRepository.save(movie2);

        Movie movie3 = new Movie();
        movie3.setName("영화3");
        movieRepository.save(movie3);

        Movie movie4 = new Movie();
        movie4.setName("영화4");
        movieRepository.save(movie4);

        Movie movie5 = new Movie();
        movie5.setName("영화5");
        movieRepository.save(movie5);

        //상영관 생성//
        Theater theater1 = new Theater();
        theater1.setName("A극장");
        theaterRepository.save(theater1);

        Theater theater2 = new Theater();
        theater2.setName("B극장");
        theaterRepository.save(theater2);

        Theater theater3 = new Theater();
        theater3.setName("C극장");
        theaterRepository.save(theater3);

        //상영정보 생성//
        Long showId1 = showService
                .show(movie1.getId(), theater1.getId(), LocalDateTime.of(2023, 7, 7, 1, 0));

        Long showId2 = showService
                .show(movie1.getId(), theater1.getId(), LocalDateTime.of(2023, 7, 7, 2, 0));

        Long showId3 = showService
                .show(movie3.getId(), theater2.getId(), LocalDateTime.of(2023, 7, 7, 3, 0));

        Long showId4 = showService
                .show(movie4.getId(), theater2.getId(), LocalDateTime.of(2023, 7, 7, 1, 0));

        Long showId5 = showService
                .show(movie5.getId(), theater3.getId(), LocalDateTime.of(2023, 7, 7, 1, 0));

        List<Movie> movies = em.createQuery
                        ("select m from Movie m join fetch m.shows", Movie.class)
                .getResultList();
        for (Movie movie: movies) {
            System.out.println("haha");
            System.out.println(movie.getName() + "상영정보: " + movie.getShows());
        }
    }

    //영화 -> 상영관, 상영시간 조회
    /*
    @Test
    @Transactional
    @Commit
    public void 영화_상영정보_조회() {
        List<Movie> movies = em.createQuery
                        ("select m from Movie m join fetch m.shows", Movie.class)
                .getResultList();
        if (movies.size()==0)
            System.out.println("hoho");
        for (Movie movie: movies) {
            System.out.println("haha");
            System.out.println(movie.getName() + "상영정보: " + movie.getShows());
        }
    }

     */


    //상영관 -> 영화, 상영시간 조회

    @Test
    public void 상영관_상영정보_조회() {

    }
    //회원 가입
    @Test
    public void 회원가입() {

    }

    //평점 생성
    @Test
    public void 영화평가() {

    }

    //회원 -> 영화, 평점 조회
    @Test
    public void 회원_평점_조회() {

    }

    //영화 -> 회원, 평점 조회
    @Test
    public void 영화_평점_조회() {

    }
}
