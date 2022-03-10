package com.example.shopping.domain.board;


import com.example.shopping.BaseTimeEntity;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@NoArgsConstructor
public class BoardsEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    //게시판에는 식별 id, 글제목,글작성form,작성일,서브 타이틀(ex.장르)

    private String title;

    private String subTitle;

    private String text;
/*

    @ManyToOne(fetch = FetchType.LAZY)
    MemberEntity member = new MemberEntity();
*/






}
