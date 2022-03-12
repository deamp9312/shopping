package com.example.shopping.web;

import com.example.shopping.domain.board.BoardJpaRepository;
import com.example.shopping.domain.board.BoardsEntity;
import com.example.shopping.domain.member.MemberEntity;
import com.example.shopping.domain.member.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class testData {

    private final MemberJpaRepository memberJpaRepository;
    private final BoardJpaRepository boardJpaRepository;

    @PostConstruct
    public void init(){
        MemberEntity initMember = new MemberEntity("1", "1", "1");
        memberJpaRepository.save(initMember);


        List<BoardsEntity> boards = new ArrayList<>();
        for(int i=1;i<20;i++) {
            boards.add(BoardsEntity.CreateBoard("" + i, "" + i, "" + i, initMember));
        }
        boardJpaRepository.saveAll(boards);
    }

}
