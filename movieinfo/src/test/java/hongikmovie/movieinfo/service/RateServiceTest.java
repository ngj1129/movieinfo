package hongikmovie.movieinfo.service;

import hongikmovie.movieinfo.MovieinfoApplication;
import hongikmovie.movieinfo.domain.Member;
import hongikmovie.movieinfo.domain.Movie;
import hongikmovie.movieinfo.domain.Rate;
import hongikmovie.movieinfo.domain.RateStatus;
import hongikmovie.movieinfo.repository.RateRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MovieinfoApplication.class)
@Transactional
class RateServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    RateService rateService;

    @Autowired
    RateRepository rateRepository;

    @Test
    public void 영화평가() throws Exception {
        Member member = createMember("나경주");

        Movie movie = createMovie("스파이더맨");

        float rateScore = 5;

        Long rateId = rateService.rate(member.getId(), movie.getId(), rateScore);

        Rate getRate = rateRepository.findOne(rateId);

        assertEquals(RateStatus.RATED, getRate.getStatus(), "평가완료상태 확인");

        Movie movie2 = createMovie("인어공주");

        float rateScore2 = 3.5f;

        Long rateId2 = rateService.rate(member.getId(), movie2.getId(), rateScore2);

        Member member3 = createMember("나경서");
        Long rateId3 = rateService.rate(member3.getId(), movie.getId(), rateScore2);

        List<Rate> ratings = member.getRatings();
        for (Rate rating: ratings) {
            System.out.println(rating.getMovie().getName());
            System.out.println(rating.getScore());
        }

        List<Rate> ratings2 = movie.getRatings();
        for (Rate rt: ratings2) {
            System.out.println(rt.getMember().getName());
            System.out.println(rt.getScore());
        }

    }

    private Movie createMovie(String name) {
        Movie movie = new Movie();
        movie.setName(name);
        em.persist(movie);
        return movie;
    }
    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        em.persist(member);
        return member;
    }

}