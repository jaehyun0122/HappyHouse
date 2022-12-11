package com.example.back.api.service.user;

import com.example.back.api.Entity.User;
import com.example.back.api.dto.JoinDTO;
import com.example.back.api.dto.LoginDTO;
import com.example.back.api.dto.UserDTO;

public interface UserService {
    UserDTO login(LoginDTO member) throws Exception;
    UserDTO info(String nickname) throws Exception;
    boolean regMember(JoinDTO joinDTO) throws Exception;
    boolean deleteMember(String nickname) throws Exception;
}
