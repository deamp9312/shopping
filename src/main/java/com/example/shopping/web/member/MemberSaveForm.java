package com.example.shopping.web.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class MemberSaveForm {

    @Size(max = 20,min = 1,message = "아이디는 최소 1글자 이상 20자 미만이여야 합니다.")
    @NotBlank(message = "아이디는 필수입니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수입니다.")
  /*  @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
  */  private String password;

    @Size(max = 20,message = "이름은 최대 20자 까지 허용합니다.")
    @NotBlank(message = "이름은 필수입니다.")
    private String userName;

    public MemberSaveForm(String userId, String password, String userName) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
    }
}
