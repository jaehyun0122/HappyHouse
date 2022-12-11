package com.example.back.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int id;
    private int writerId;
    private int boardId;
    private String content;
    private String regtime;
}
