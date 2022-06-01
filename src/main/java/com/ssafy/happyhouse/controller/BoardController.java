package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.model.dto.BoardParameter;
import com.ssafy.happyhouse.model.dto.Comment;
import com.ssafy.happyhouse.model.service.BoardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeArticle(@RequestBody @ApiParam(value = "게시글 정보.", required = true) Board board) throws Exception {
		logger.info("writeArticle - 게시글 작성 호출"+board);
		System.out.println(board.getBoardId());
		if(boardService.writeArticle(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Board>> listArticle(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameter boardParameter) throws Exception {
		logger.info("listArticle - 게시판 글목록 호출");
		return new ResponseEntity<List<Board>>(boardService.listArticle(boardParameter), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/list")
	public ResponseEntity<List<Board>> listArticle2(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameter boardParameter) throws Exception {
		logger.info("listArticle - 게시판 글목록 호출");
		return new ResponseEntity<List<Board>>(boardService.listArticle2(boardParameter), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Board.class)
	@GetMapping("/{boardId}")
	public ResponseEntity<Board> getArticle(@PathVariable("boardId") @ApiParam(value = "얻어올 글의 글번호.", required = true) int boardId) throws Exception {
		logger.info("getArticle - 호출");
		boardService.updateHit(boardId);
		return new ResponseEntity<Board>(boardService.getArticle(boardId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글수정", notes = "새로운 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) Board board) throws Exception {
		logger.info("modifyArticle - 호출");
		if(boardService.modifyArticle(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{boardId}")
	public ResponseEntity<String> deleteArticle(@PathVariable("boardId") @ApiParam(value = "살제할 글의 글번호.", required = true) int boardId) throws Exception {
		logger.info("deleteArticle - 호출");
		if(boardService.deleteArticle(boardId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	// pagination처리 위해 총 게시글 개수를 반환하기 위한 매핑.
	@ApiOperation(value = "총 게시글 개수", notes = "총 게시글 개수 반환", response = List.class)
	@GetMapping("/totalCount")
	public ResponseEntity<Integer> getTotalCount(@ApiParam(value = "총 게시글 개수", required = true) BoardParameter boardParameter) throws Exception {
		logger.info("getTotalCount - 호출");
		System.out.println(boardService.getTotalCount(boardParameter));
		return new ResponseEntity<Integer>(boardService.getTotalCount(boardParameter), HttpStatus.OK);
	}
	// 해당 게시글의 댓글을 가져오기위해
	@ApiOperation(value = "댓글 목록", notes = "모든 댓글 정보를 반환한다.", response = List.class)
	@GetMapping("/memo/{boardId}")
	public ResponseEntity<List<Comment>> listMemo(@PathVariable("boardId") @ApiParam(value = "댓글을 얻기위한 부가정보.", required = true) int boardId) throws Exception {
		logger.info("getMemo - 호출"+boardId);
		return new ResponseEntity<List<Comment>>(boardService.listComment(boardId), HttpStatus.OK);
	}
	
	// memo
	@PostMapping("/memo")
	public ResponseEntity<String> writeComment(@RequestBody @ApiParam(value = "댓글 작성.", required = true) Comment comment) throws Exception {
		logger.info("writeMemo - 호출"+comment);
		if (boardService.writeComment(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 삭제", notes = "댓글 삭제", response = String.class)
	@DeleteMapping("/memo/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable("id") @ApiParam(value = "삭제할 댓글 번호.", required = true) int id) throws Exception {
		logger.info("deleteComment - 호출"+id);
		if (boardService.deleteComment(id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}
