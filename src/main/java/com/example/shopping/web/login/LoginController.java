package com.example.shopping.web.login;


import com.example.shopping.SessionConst;
import com.example.shopping.domain.member.MemberEntity;
import com.example.shopping.domain.member.MemberServices;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final MemberServices memberServices;

    //login
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("member") LoginMemberForm memberLoginForm) {
        return "/members/loginForm";
    }

    @PostMapping("/login")
    public String MemberLogin(@RequestParam(defaultValue = "/")String redirectURL,
            @Validated @ModelAttribute("member") LoginMemberForm memberLoginForm,
                              BindingResult bindingResult,
                              HttpServletRequest request) {
        System.out.println("redirectURL = " + redirectURL);

        if (bindingResult.hasErrors()) {
            return "/members/loginForm";
        }

        Object loginMember = memberServices.findMember(memberLoginForm.getUserId(), memberLoginForm.getPassword());
        if (loginMember instanceof String) {
            bindingResult.reject("loginFail", (String) loginMember);
            return "/members/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        System.out.println("redirectURL = " + redirectURL);


        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
