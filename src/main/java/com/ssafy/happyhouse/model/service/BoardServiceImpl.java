package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.model.dto.BoardParameter;
import com.ssafy.happyhouse.model.dto.Comment;
import com.ssafy.happyhouse.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Board> listArticle(BoardParameter boardParameter) throws Exception {
		int start = boardParameter.getPg() == 0? 0 : (boardParameter.getPg() - 1)*boardParameter.getSpp();
		boardParameter.setStart(start);
		return sqlSession.getMapper(BoardMapper.class).listArticle(boardParameter);
	}
	
	@Override
	public List<Board> listArticle2(BoardParameter boardParameter) throws Exception {
		int start = boardParameter.getPg() == 0? 0 : (boardParameter.getPg() - 1)*boardParameter.getSpp();
		boardParameter.setStart(start);
		return sqlSession.getMapper(BoardMapper.class).listArticle2(boardParameter);
	}
	
	@Override
	public boolean writeArticle(Board board) throws Exception {
		if(board.getTitle() == null || board.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeArticle(board)==1;
	}

	@Override
	public Board getArticle(int boardId) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getArticle(boardId);
	}

	@Override
	public void updateHit(int boardId) throws Exception {
		sqlSession.getMapper(BoardMapper.class).updateHit(boardId);
	}

	@Override
	public boolean modifyArticle(Board board) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).modifyArticle(board)==1;
	}

	@Override
	public boolean deleteArticle(int boardId) throws Exception {
		sqlSession.getMapper(BoardMapper.class).deleteArticle(boardId);
		
		return sqlSession.getMapper(BoardMapper.class).deleteArticle(boardId)==1;
	}

	@Override
	public int getTotalCount(BoardParameter boardParameter) throws Exception {
		sqlSession.getMapper(BoardMapper.class).getTotalCount(boardParameter);
		return sqlSession.getMapper(BoardMapper.class).getTotalCount(boardParameter);
	}



	@Override
	public boolean writeComment(Comment comment) throws Exception {
		if(comment.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeComment(comment) == 1;
	}

	@Override
	public List<Comment> listComment(int boardId) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).listComment(boardId);
	}

	@Override
	public boolean deleteComment(int memono) {
		sqlSession.getMapper(BoardMapper.class).deleteComment(memono);
		return true;
	}



}