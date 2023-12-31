package hongikmovie.movieinfo.repository;

import hongikmovie.movieinfo.domain.Movie;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepository {

    private final EntityManager em;

    public void save(Movie movie) {
        if (movie.getId() == null) {
            em.persist(movie);
        } else {
            em.merge(movie);
        }
    }

    public Movie findOne(Long id) { return em.find(Movie.class, id); }

    public List<Movie> findAll() {
        return em.createQuery
                        ("select m from Movie m", Movie.class)
                .getResultList();
    }
}
