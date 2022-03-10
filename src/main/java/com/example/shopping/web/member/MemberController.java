package com.example.shopping.web.member;

import com.example.shopping.domain.member.MemberServices;
import com.example.shopping.web.login.LoginMemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequestMapping("/members")
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberServices memberServices;


    //회원 가입
    @GetMapping("/new")
    public String MemberForm(@ModelAttribute("member") MemberSaveForm memberSaveForm) {
        return "/members/newMember";
    }

    @PostMapping("/new")
    public String MemberSave(@Validated @ModelAttribute("member") MemberSaveForm memberSaveForm, BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/members/newMember";
        }

        String saveMember = memberServices.saveMember(memberSaveForm);
        if(saveMember==null){
            bindingResult.addError(new ObjectError("existenceMember", "아이디가 이미 존재합니다."));
            return "/members/newMember";
        }
        redirectAttributes.addAttribute("status", true);

        return "redirect:/";
    }



}
