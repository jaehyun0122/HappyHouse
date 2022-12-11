package com.example.back.api.repository.board;

import com.example.back.api.Entity.Board;
import com.example.back.api.dto.BoardMemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {
    Page<BoardMemberDTO> getAllBoard(Pageable pageable);
}
