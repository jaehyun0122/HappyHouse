package com.example.back.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardMemberDTO {
    private int boardId;
    private String title;
    private String content;
    private String username;

    @QueryProjection
    public BoardMemberDTO(int boardId, String title, String content, String username){
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.username = username;
    }
}
