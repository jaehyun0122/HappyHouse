package com.example.back.api.service.board;

import com.example.back.api.Entity.*;
import com.example.back.api.dto.CommentDto;
import com.example.back.api.repository.UserRepository;
import com.example.back.api.repository.board.BoardRepository;
import com.example.back.api.repository.comment.CommentRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.example.back.api.Entity.QBoard.*;
import static com.example.back.api.Entity.QBoard.board;
import static com.example.back.api.Entity.QComment.*;
import static com.example.back.api.Entity.QUser.*;

@Service
public class BoardServiceImple implements BoardService{

    @PersistenceContext
    private EntityManager em;

    private JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Page<Board> getBoard(Pageable pageable){
        List<Board> findBoard = queryFactory
                .select(board)
                .from(board)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(board.count())
                .from(board)
                .fetchOne();

        return new PageImpl<>(findBoard, pageable, total);
    }

    @Override
    public boolean writeArticle(Board board) throws Exception {
        try{
            boardRepository.save(board);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateHit(int boardId) throws Exception {
        try {

            Integer boardHit = queryFactory
                    .select(board.hit)
                    .from(board)
                    .where(board.id.eq(boardId))
                    .fetchOne();

            int plusHit = boardHit+1;

            long execute = queryFactory
                    .update(board)
                    .set(board.hit, plusHit)
                    .where(board.id.eq(boardId))
                    .execute();
            em.flush();
            em.clear();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean modifyArticle(Board board) throws Exception {
        try{
            long execute = queryFactory
                    .update(QBoard.board)
                    .set(QBoard.board.title, board.getTitle())
                    .set(QBoard.board.content, board.getContent())
                    .where(QBoard.board.id.eq(board.getId()))
                    .execute();
            em.flush();
            em.clear();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteArticle(int boardId) throws Exception {
        try {
            long execute = queryFactory
                    .delete(board)
                    .where(board.id.eq(boardId))
                    .execute();
            em.flush();
            em.clear();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean writeComment(CommentDto commentDto) throws Exception {
        try{
            User findUser = queryFactory
                    .selectFrom(QUser.user)
                    .where(QUser.user.id.eq(commentDto.getWriterId()))
                    .fetchOne();
            Board findBoard = queryFactory
                    .selectFrom(QBoard.board)
                    .where(QBoard.board.id.eq(commentDto.getBoardId()).and(comment.id.eq(commentDto.getId())))
                    .fetchOne();
            Comment comment = new Comment(commentDto.getContent(), findUser, findBoard);

            commentRepository.save(comment);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Comment> listComment(int boardId) throws Exception {
        List<Comment> result = queryFactory
                .selectFrom(comment)
                .where(comment.board.id.eq(boardId))
                .fetch();
        return result;
    }

    @Override
    public boolean deleteComment(int memono) {
        try{
            long execute = queryFactory
                    .delete(comment)
                    .where(comment.id.eq(memono))
                    .execute();
            em.flush();
            em.clear();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
