package com.example.back.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinDTO {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String regtime;
}

