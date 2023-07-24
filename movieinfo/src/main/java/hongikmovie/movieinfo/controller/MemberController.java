package hongikmovie.movieinfo.controller;

import hongikmovie.movieinfo.domain.Member;
import hongikmovie.movieinfo.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("member")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/login")
    public String loginForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/loginMemberForm";
    }

    @PostMapping("/members/login")
    public String login(@Valid MemberForm form, BindingResult result, Model model) {

        Member findMember = memberService.login(form.getEmail(), form.getPassword());

        if (result.hasErrors()) {
            return "members/loginMemberForm";
        }

        if (findMember != null) {
            model.addAttribute("member", findMember);
            return "redirect:/";
        }


        return "members/loginMemberForm";
    }

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());

        memberService.join(member);
        return "redirect:/";
    }

}
