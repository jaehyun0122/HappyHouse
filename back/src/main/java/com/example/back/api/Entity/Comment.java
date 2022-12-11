package com.example.back.api.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private int id;
    private String content;
    private String regtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(String content, User user, Board board){
        this.content = content;
        if(user != null){
            insertUser(user);
        }
        if(board != null){
            insertBoard(board);
        }
    }

    private void insertUser(User user){
        this.user = user;
        user.getComments().add(this);
    }
    private void insertBoard(Board board){
        this.board = board;
        board.getComments().add(this);
    }

}
