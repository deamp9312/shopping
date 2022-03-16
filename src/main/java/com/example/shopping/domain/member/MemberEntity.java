package com.example.shopping.domain.member;

import com.example.shopping.BaseTimeEntity;
import com.example.shopping.domain.board.BoardsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class MemberEntity extends BaseTimeEntity {

    //member 정보는 id,pwd,board와 1:n관계
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userId;

    private String password;

    private String userName;

    @OneToMany(mappedBy = "member")
    List<BoardsEntity> boards = new ArrayList<>();


    @Override
    public String toString() {
        return "MemberEntity{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public MemberEntity(String userId, String password, String userName) {
        this.userId = userId;
        this.password = password;
        this.userName=userName;
    }




}
