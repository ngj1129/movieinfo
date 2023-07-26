package hongikmovie.movieinfo.service;

import hongikmovie.movieinfo.domain.Member;
import hongikmovie.movieinfo.domain.Movie;
import hongikmovie.movieinfo.domain.Rate;
import hongikmovie.movieinfo.domain.Theater;
import hongikmovie.movieinfo.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;

    @Transactional
    public void saveTheater(Theater theater) { theaterRepository.save(theater); }

    public List<Theater> findTheaters() { return theaterRepository.findAll(); }

    public Theater findOne(Long theaterId) { return theaterRepository.findOne(theaterId); }

    public List<Theater> findTheaters(Movie movie) {
        return theaterRepository.findByMovie(movie);
    }
}
