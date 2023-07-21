package hongikmovie.movieinfo.service;

import hongikmovie.movieinfo.domain.Member;
import hongikmovie.movieinfo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //중복 회원 검증 나중에
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() { return memberRepository.findAll(); }

    public Member findOne(Long memberId) { return memberRepository.findOne(memberId); }


}
