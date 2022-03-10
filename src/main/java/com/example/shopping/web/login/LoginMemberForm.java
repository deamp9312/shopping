package com.example.shopping.web.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginMemberForm {

    @NotBlank(message = "아이디를 입력해 주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;

    private String userName;

    public LoginMemberForm(String userId, String password, String userName) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
    }
}
