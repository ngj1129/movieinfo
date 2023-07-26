package hongikmovie.movieinfo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "shows")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Show {

    @Id
    @GeneratedValue
    @Column(name = "shows_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    private LocalDateTime showTime;

    //영화의 상영관 리스트에 추가//
    public void setMovie(Movie movie) {
        this.movie = movie;
        movie.getShows().add(this);
    }

    //상영관의 상영 영화 리스트에 추가//
    public void setTheater(Theater theater) {
        this.theater = theater;
        theater.getShows().add(this);
    }

    //상영 정보 생성//
    public static Show createShow(Movie movie, Theater theater, LocalDateTime showTime) {
        Show show = new Show();
        show.setMovie(movie);
        show.setTheater(theater);
        show.setShowTime(showTime);
        return show;
    }

}
