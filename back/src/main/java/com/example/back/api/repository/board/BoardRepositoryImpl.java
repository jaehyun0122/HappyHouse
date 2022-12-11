package com.example.back.api.repository.board;

import com.example.back.api.dto.BoardMemberDTO;
import com.example.back.api.dto.QBoardMemberDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.back.api.Entity.QBoard.board;
import static com.example.back.api.Entity.QUser.user;

@Slf4j
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<BoardMemberDTO> getAllBoard(Pageable pageable) {
        List<BoardMemberDTO> content = queryFactory
                .select(new QBoardMemberDTO(
                        board.id.as("board_id"),
                        board.title,
                        board.content,
                        user.name
                ))
                .from(board)
                .leftJoin(board.user, user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(board.count())
                .from(board)
                .leftJoin(board.user, user)
                .fetchOne();

        return new PageImpl<BoardMemberDTO>(content, pageable, total);
    }
}
