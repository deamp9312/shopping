package com.example.shopping.web.board;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BoardDto {


    private String writer;    //작성자

    private String title;   //제목

    private String text;    //본문


    //아직 미 구현
    private Long like_status;   //해당 사용자가 좋아요를 눌렀을때 빈 하트 or 풀 하트 택하게..?

    private Long likes;          //총 좋아요 횟수


    private Long visit;         //방문 횟수수 아직 구현 x
}
