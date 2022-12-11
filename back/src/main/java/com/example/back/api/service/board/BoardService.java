package com.example.back.api.service.board;

import com.example.back.api.Entity.Board;
import com.example.back.api.Entity.Comment;
import com.example.back.api.dto.BoardDto;
import com.example.back.api.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BoardService {

    Page<Board> getBoard(Pageable pageable);
    boolean writeArticle(Board board) throws Exception;

    void updateHit(int boardId) throws Exception;
    boolean modifyArticle(Board board) throws Exception;
    boolean deleteArticle(int boardId) throws Exception;

    // memo
    boolean writeComment(CommentDto commentDto) throws Exception;
    List<Comment> listComment(int boardId) throws Exception;
    boolean deleteComment(int memono);
}
