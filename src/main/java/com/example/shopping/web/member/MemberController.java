package com.example.shopping.web.member;

import com.example.shopping.domain.member.MemberEntity;
import com.example.shopping.domain.member.MemberJpaRepository;
import com.example.shopping.domain.member.MemberServices;
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
    private final MemberJpaRepository memberJpaRepository;

    //회원 가입
    @GetMapping("/new")
    public String MemberForm(@ModelAttribute("member") MemberSaveForm memberSaveForm) {

        return "/members/addMember";
    }

    @PostMapping("/new")
    public String MemberSave(@Validated @ModelAttribute("member") MemberSaveForm memberSaveForm, BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {

       if (bindingResult.hasErrors()) {
            return "/members/addMember";
        }

        MemberEntity newMember = new MemberEntity(memberSaveForm.getUserId(), memberSaveForm.getPassword(), memberSaveForm.getUserName());
        String saveMember = memberServices.saveMember(newMember);
        if(saveMember==null){
            bindingResult.addError(new ObjectError("existenceMember", "아이디가 이미 존재합니다."));
            return "/members/addMember";
        }

        redirectAttributes.addAttribute("status", true);
        return "redirect:/";
    }



}
