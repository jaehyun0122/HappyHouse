package com.ssafy.happyhouse.model.service;

import java.util.List;


import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.model.dto.BoardParameter;
import com.ssafy.happyhouse.model.dto.Comment;

public interface BoardService {
	public boolean writeArticle(Board board) throws Exception;
	public List<Board> listArticle(BoardParameter boardParameter) throws Exception;
	public List<Board> listArticle2(BoardParameter boardParameter) throws Exception;
	
	public Board getArticle(int boardId) throws Exception;
	public void updateHit(int boardId) throws Exception;
	public boolean modifyArticle(Board board) throws Exception;
	public boolean deleteArticle(int boardId) throws Exception;
	
	// 총 페이지 수 반환
	public int getTotalCount(BoardParameter boardParameter) throws Exception;
	// memo
	public boolean writeComment(Comment comment) throws Exception;
	public List<Comment> listComment(int boardId) throws Exception;
	public boolean deleteComment(int memono);
}
