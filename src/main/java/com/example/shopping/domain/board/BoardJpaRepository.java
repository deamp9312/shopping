package com.example.shopping.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardJpaRepository extends JpaRepository<BoardsEntity, Long> {


    @Query("select b.id from BoardsEntity b where b.id = :id")
    BoardsEntity findBoard(@Param("id") Long id);

}
