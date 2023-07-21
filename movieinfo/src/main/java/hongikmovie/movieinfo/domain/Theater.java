package hongikmovie.movieinfo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Theater {

    @Id @GeneratedValue
    @Column(name = "theater_id")
    private Long id;

    private String name;

    //상영관 주소 추가하기

    @OneToMany(mappedBy = "theater")
    private List<Show> shows = new ArrayList<>();
}
