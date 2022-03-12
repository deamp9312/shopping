package com.example.shopping.web.board;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BoardSaveForm {

    //update 장르는 enum으로 제공할거 같음 + 유저의 등급에 따라 비공개 공개 설정가능
    // 테그 하나 더 추가할 예정임 enum 좀 공부해야 될듯.. 까먹.
    @NotBlank
    private String type;    //장르

    @NotBlank
    private String title;   //제목

    @NotBlank
    private String text;    //본문



}
