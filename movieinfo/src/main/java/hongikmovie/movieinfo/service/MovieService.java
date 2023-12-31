package hongikmovie.movieinfo.service;

import hongikmovie.movieinfo.domain.Movie;
import hongikmovie.movieinfo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public void saveMovie(Movie movie) { movieRepository.save(movie); }

    public List<Movie> findMovies() { return movieRepository.findAll(); }

    public Movie findOne(Long movieId) { return movieRepository.findOne(movieId); }

}
