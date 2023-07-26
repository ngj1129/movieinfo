package hongikmovie.movieinfo.repository;

import hongikmovie.movieinfo.domain.Member;
import hongikmovie.movieinfo.domain.Rate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RateRepository {

    private final EntityManager em;

    public void save(Rate rate) { em.persist(rate); }

    public Rate findOne(Long id) { return em.find(Rate.class, id); }

    public List<Rate> findByEmail(Member member) {
        return em.createQuery
                        ("select r from Rate r where r.member = :member", Rate.class)
                .setParameter("member", member)
                .getResultList();
    }

    /*
    public List<Rate> findAllByEmail(String email) {
        //language=JPAQL
        String jpql = "select r From Rate r join r.member m";
        boolean isFirstCondition = true;
        //회원 이름 검색
        if (StringUtils.hasText(email)) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.email like :email";
        }
        TypedQuery<Rate> query = em.createQuery(jpql, Rate.class)
                .setMaxResults(1000); //최대 1000건
        if (StringUtils.hasText(email)) {
            query = query.setParameter("email", email);
        }
        return query.getResultList();
    }

     */

}

