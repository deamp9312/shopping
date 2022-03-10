package com.example.shopping.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<BoardsEntity,Long> , BoardRepository{
}
