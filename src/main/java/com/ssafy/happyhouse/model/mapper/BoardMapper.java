package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.model.dto.BoardParameter;
import com.ssafy.happyhouse.model.dto.Comment;

@Mapper
public interface BoardMapper {
	
	public int writeArticle(Board board) throws SQLException;
	public List<Board> listArticle(BoardParameter boardParameter) throws SQLException;
	public List<Board> listArticle2(BoardParameter boardParameter) throws SQLException;
	public int getTotalCount(BoardParameter boardParameter) throws SQLException;
	public Board getArticle(int boardId) throws SQLException;
	public void updateHit(int boardId) throws SQLException;
	public int modifyArticle(Board board) throws SQLException;
//	public void deleteComment(int boardId) throws SQLException;
	public int deleteArticle(int boardId) throws SQLException;
	//memo
	public int writeComment(Comment comment);
	public List<Comment> listComment(int boardId) throws SQLException;
	public void deleteComment(int memono);
	
}