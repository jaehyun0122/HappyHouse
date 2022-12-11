package com.example.back.api.Entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@ToString(of = {"id", "username", "email"})
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private int id;
    private String nickname;
    private String password;
    private String name;
    private String email;
    private String regtime;

    @Builder
    public User(String email, String nickname, String name, String password, String regtime){
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.password = password;
        this.regtime = regtime;
    }

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

}
