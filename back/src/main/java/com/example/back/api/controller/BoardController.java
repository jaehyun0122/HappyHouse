package com.example.back.api.controller;

import com.example.back.api.dto.BoardDto;
import com.example.back.api.dto.BoardMemberDTO;
import com.example.back.api.repository.board.BoardRepository;
import com.example.back.api.service.board.BoardServiceImple;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api")
@Api("게시판 컨트롤러")
public class BoardController {

    @Autowired
    BoardServiceImple boardService;

    @Autowired
    BoardRepository boardRepository;


    @GetMapping("/board")
    @ApiOperation(value = "게시판 정보 가져오기", notes = "Pageable 정보를 받아 총 게시글 수, 정보 리턴")
    public Page<BoardMemberDTO> getAllBoard(Pageable pageable){
        log.info("pageable 정보 : {}",pageable);
        Page<BoardMemberDTO> board = boardRepository.getAllBoard(pageable);

        return board;
    }

    @PostMapping("/board")
    @ApiOperation(value = "게시글 작성", notes = "게시글 제목, 내용을 입력받아 저장")
    public ResponseEntity createBoard(@RequestBody BoardDto boardDto){
        log.info("post board data : {}",boardDto.toString());
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/board/{boardid}")
    @ApiOperation(value = "게시글 삭제")
    public ResponseEntity deleteBoard(
            @PathVariable("boardId") int boardId){

        try {
            boardService.deleteArticle(boardId);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

//    @PutMapping("/board/{boardId}")
//    @ApiOperation(value = "게시글 수정")
//    public ResponseEntity updateBoard(
//            @PathVariable("boardId") int boardId,
//            @RequestParam String title,
//            @RequestParam String content
//    ){
//        try{
//            boardService.updateBoard(boardId, title, content);
//            return new ResponseEntity(HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//
//    }


}
