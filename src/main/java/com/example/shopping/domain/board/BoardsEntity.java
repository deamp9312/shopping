package com.example.shopping.domain.board;


import com.example.shopping.BaseTimeEntity;
import com.example.shopping.domain.member.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class BoardsEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    //게시판에는 식별 id, 글제목,글작성form,작성일,서브 타이틀(ex.장르)

    private String writer;    //작성자

    private String title; //제목

    private String text;    //본문

    //enum 으로 비공개 공개. 개인 설정해서 공개 외에는 비공개는 본인만 볼수 있게 설정.
//    privacy grade ->일반 vip로 설정해서 vip는 매달 천원의 이용료 발생? ㅋㅋ.

    //이건 아직 구현 no
    //금액적인것도 코딩해두자... 퍼센트할인 정가 할인.
    //포인트제로 좋아요를 받은 숫자 = 1point 그것을 비공개 글 쓸때 사용가능
    //비공개 글은 글자 수 만큼 like 소모..?

    //조회수 , 좋아요
    private Long like_status;   //해당 사용자가 좋아요를 눌렀을때 빈 하트 or 풀 하트 택하게..?

    private Long likes;//총 좋아요 횟수


    private Long visit; //방문 횟수수 아직 구현 x

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    MemberEntity member = new MemberEntity();


    public void setMember(MemberEntity member) {
        if(this.member != null) {
            this.member.getBoards().remove(this);
        }
        this.member = member;
        member.getBoards().add(this);
    }

    @Override
    public String toString() {
        return "BoardsEntity{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", like_status=" + like_status +
                ", likes=" + likes +
                ", visit=" + visit +
                '}';
    }

    public BoardsEntity CreateBoard(String writer, String title, String text) {
        BoardsEntity board = new BoardsEntity();
        board.writer = writer;
        board.title =title;
        board.text = text;
        board.like_status = 0L;
        board.likes = 0L;
        board.visit = 0L;

        return board;

    }
}
