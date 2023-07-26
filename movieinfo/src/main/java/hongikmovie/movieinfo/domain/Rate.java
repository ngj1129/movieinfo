package hongikmovie.movieinfo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rate {

    @Id
    @GeneratedValue
    @Column(name = "rate_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Enumerated(EnumType.STRING)
    private RateStatus status = RateStatus.NOTRATED;

    private float score;

    //회원의 평점 리스트에 추가//
    public void setMember(Member member) {
        this.member = member;
        member.getRatings().add(this);
    }

    //영화의 평점 리스트에 추가
    public void setMovie(Movie movie) {
        this.movie = movie;
        movie.getRatings().add(this);
    }

    //평점 생성//
    public static Rate doRate(Member member, Movie movie, float score) {
        Rate rate = new Rate();
        rate.setMember(member);
        rate.setMovie(movie);
        rate.setStatus(RateStatus.RATED);
        rate.setScore(score);
        return rate;
    }


}
