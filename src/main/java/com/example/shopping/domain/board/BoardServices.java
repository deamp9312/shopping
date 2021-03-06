package com.example.shopping.domain.board;


import com.example.shopping.domain.member.MemberEntity;
import com.example.shopping.domain.member.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServices {
    private final BoardJpaRepository boardJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    //반환은 board 의 id를 반환해줘야 함.
    @Transactional
    public Long saveBoard(BoardsEntity board, Long member_id) {
        MemberEntity member = memberJpaRepository.findMemberById(member_id);

        board.setMember(member);
        BoardsEntity savedBoard = boardJpaRepository.save(board);

        return savedBoard.getId();
    }

    public List<BoardsEntity> findAllBoard(){
        return boardJpaRepository.findAll();
    }

    //board list에서 id로 선택한 board를 불러오기 위해 사용.
    public BoardsEntity findBoard(Long board_id){
        return boardJpaRepository.findBoard(board_id);
    }


    @Transactional
    public void viewCountUp(Long board_key) {
        Optional<BoardsEntity> board = boardJpaRepository.findById(board_key);
        Long visit = board.get().getVisit();
        board.get().ChangeVisit(++visit);

    }
}
