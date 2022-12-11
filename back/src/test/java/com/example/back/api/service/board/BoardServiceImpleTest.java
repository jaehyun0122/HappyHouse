package com.example.back.api.service.board;

import com.example.back.api.Entity.Board;
import com.example.back.api.Entity.User;
import com.example.back.api.Entity.QBoard;
import com.example.back.api.dto.BoardDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BoardServiceImpleTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

//    @Test
//    @Transactional
//    public void create(){
//        User user = new User(
//                "test@test",
//                "testName",
//                "fsfdsf/png",
//                "202201"
//        );
//        em.persist(user);
//        BoardDto boardDto = new BoardDto("title", "content");
//
//        Board board1 = new Board(boardDto.getTitle(), boardDto.getContent(), user);
//
//        em.persist(board1);
//
//        Board findBoard = queryFactory
//                .select(QBoard.board)
//                .from()
//                .where(QBoard.board.title.eq("title"))
//                .fetchOne();
//
//        assertThat(findBoard.getTitle()).isEqualTo("title");
//        assertThat(findBoard.getUser().getEmail()).isEqualTo("test@test");
//
//    }

}
