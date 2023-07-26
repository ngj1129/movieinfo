package hongikmovie.movieinfo.repository;

import hongikmovie.movieinfo.domain.Movie;
import hongikmovie.movieinfo.domain.Show;
import hongikmovie.movieinfo.domain.Theater;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShowRepository {

    private final EntityManager em;

    public void save(Show show) { em.persist(show); }

    public Show findOne(Long id) { return em.find(Show.class, id); }

    public List<Show> findShowsByMovieAndTheater(Long movieId, Long theaterId) {
        String jpql = "SELECT s FROM Show s WHERE s.movie.id = :movieId AND s.theater.id = :theaterId";
        TypedQuery<Show> query = em.createQuery(jpql, Show.class);
        query.setParameter("movieId", movieId);
        query.setParameter("theaterId", theaterId);
        return query.getResultList();
    }

}

