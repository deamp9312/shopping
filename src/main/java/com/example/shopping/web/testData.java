package com.example.shopping.web;

import com.example.shopping.domain.board.BoardJpaRepository;
import com.example.shopping.domain.board.BoardServices;
import com.example.shopping.domain.board.BoardsEntity;
import com.example.shopping.domain.member.MemberEntity;
import com.example.shopping.domain.member.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class testData {

    private final MemberJpaRepository memberJpaRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final BoardServices boardServices;

    @PostConstruct
    public void init(){
        MemberEntity initMember = new MemberEntity("1", "1", "1");
        String userName = initMember.getUserName();
        MemberEntity save = memberJpaRepository.save(initMember);

        List<BoardsEntity> boards = new ArrayList<>();
        for(int i=1;i<20;i++) {
            boards.add( new BoardsEntity().CreateBoard(userName,""+ i, ""+i));
        }
        boardJpaRepository.saveAll(boards);


    }

}
