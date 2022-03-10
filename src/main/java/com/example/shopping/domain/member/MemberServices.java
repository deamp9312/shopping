package com.example.shopping.domain.member;

import com.example.shopping.web.login.LoginMemberForm;
import com.example.shopping.web.member.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberServices {

    private final MemberJpaRepository memberJpaRepository;


    @Transactional
    public String saveMember(MemberSaveForm memberSaveForm) {
        MemberEntity newMember = new MemberEntity(memberSaveForm.getUserId().replaceAll(" ",""), memberSaveForm.getPassword().replaceAll(" ",""), memberSaveForm.getUserName().replaceAll(" ",""));
        MemberEntity findMember = memberJpaRepository.findByUserId(memberSaveForm.getUserId());
        if (findMember != null) {
            return null;
        }
        return memberJpaRepository.save(newMember).getUserName();
    }

    public Object findMember(String userId, String password) {
        MemberEntity findMember = memberJpaRepository.findByUserId(userId);

        if(findMember==null){
            return "없는 사용자 입니다";
        }


        if (password.equals(findMember.getPassword())) {
            return findMember;
        }
        return "비밀번호가 일치하지 않습니다";
    }



}
