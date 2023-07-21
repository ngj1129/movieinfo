package hongikmovie.movieinfo.repository;

import hongikmovie.movieinfo.domain.Theater;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TheaterRepository {

    private final EntityManager em;

    public void save(Theater theater) {
        if (theater.getId() == null) {
            em.persist(theater);
        } else {
            em.merge(theater);
        }
    }

    public Theater findOne(Long id) { return em.find(Theater.class, id); }

    public List<Theater> findAll() {
        return em.createQuery
                        ("select t from Theater t", Theater.class)
                .getResultList();
    }
}
