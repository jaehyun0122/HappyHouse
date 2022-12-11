package com.example.back.api.repository.board;

import com.example.back.api.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer>, BoardRepositoryCustom {
}
