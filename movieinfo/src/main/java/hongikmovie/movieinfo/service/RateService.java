package hongikmovie.movieinfo.service;

import hongikmovie.movieinfo.domain.Member;
import hongikmovie.movieinfo.domain.Movie;
import hongikmovie.movieinfo.domain.Rate;
import hongikmovie.movieinfo.repository.MemberRepository;
import hongikmovie.movieinfo.repository.MovieRepository;
import hongikmovie.movieinfo.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;

    private final MemberRepository memberRepository;

    private final MovieRepository movieRepository;

    /**
     * 평가하기
     */
    @Transactional
    public Long rate(Long memberId, Long movieId, float score) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Movie movie = movieRepository.findOne(movieId);

        //평점 생성
        Rate rate = Rate.doRate(member, movie, score);

        //평점 저장
        rateRepository.save(rate);
        return rate.getId();
    }


}
