package com.example.shopping.web.home;

import com.example.shopping.domain.member.MemberEntity;
import com.example.shopping.web.argumentresolver.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@Login MemberEntity loginMember, Model model) {


        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }


}
