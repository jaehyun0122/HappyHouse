package com.example.back.api.Entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id", "title", "content"})
@Table(name = "board")
public class Board {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private int id;

    @NotNull
    private String title;
    @NotNull
    private String content;

    private int hit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        if(user != null){
            changeUser(user);
        }
    }

    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<Comment> comments = new ArrayList<>();

    private void changeUser(User user) {
        this.user = user;
        user.getBoards().add(this);
    }
}
