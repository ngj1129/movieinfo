package hongikmovie.movieinfo.service;

import hongikmovie.movieinfo.domain.Movie;
import hongikmovie.movieinfo.domain.Show;
import hongikmovie.movieinfo.domain.Theater;
import hongikmovie.movieinfo.repository.MovieRepository;
import hongikmovie.movieinfo.repository.ShowRepository;
import hongikmovie.movieinfo.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;

    private final MovieRepository movieRepository;

    private final TheaterRepository theaterRepository;

    /**
     * 상영 정보
     */
    @Transactional
    public Long show(Long movieId, Long theaterId, LocalDateTime showTime) {

        //엔티티 조회
        Movie movie = movieRepository.findOne(movieId);
        Theater theater = theaterRepository.findOne(theaterId);

        //상영정보 생성
        Show show = Show.createShow(movie, theater, showTime);

        //상영정보 저장
        showRepository.save(show);
        return show.getId();
    }

    public List<Show> findShows(Long movieId, Long theaterId) {
        return showRepository.findShowsByMovieAndTheater(movieId, theaterId);
    }

}
