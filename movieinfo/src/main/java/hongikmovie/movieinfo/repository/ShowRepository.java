package hongikmovie.movieinfo.repository;

import hongikmovie.movieinfo.domain.Show;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShowRepository {

    private final EntityManager em;

    public void save(Show show) { em.persist(show); }

    public Show findOne(Long id) { return em.find(Show.class, id); }

}
