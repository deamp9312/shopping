package com.example.shopping.domain.member;

import com.example.shopping.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String password;

    private String userName;

/*
    @OneToMany(mappedBy = "members")
    List<BoardsEntity> boards = new ArrayList<>();
*/



    public MemberEntity(String userId, String password,String userName) {
        this.userId = userId;
        this.password = password;
        this.userName=userName;
    }
}
