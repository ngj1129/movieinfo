package hongikmovie.movieinfo.repository;

import hongikmovie.movieinfo.domain.Rate;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RateRepository {

    private final EntityManager em;

    public void save(Rate rate) { em.persist(rate); }

    public Rate findOne(Long id) { return em.find(Rate.class, id); }
}
