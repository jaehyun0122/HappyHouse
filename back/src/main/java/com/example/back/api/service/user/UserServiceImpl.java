package com.example.back.api.service.user;

import com.example.back.api.Entity.QUser;
import com.example.back.api.Entity.User;
import com.example.back.api.dto.JoinDTO;
import com.example.back.api.dto.LoginDTO;
import com.example.back.api.dto.UserDTO;
import com.example.back.api.repository.UserRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

import static com.example.back.api.Entity.QUser.*;

@Service
public class UserServiceImpl implements UserService {

    private final JPAQueryFactory queryFactory;

    public UserServiceImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO login(LoginDTO member) throws Exception {

        return null;
    }

    @Override
    public UserDTO info(String nickname) throws Exception {
        Tuple tuple = queryFactory
                .select(user.email, user.nickname, user.name)
                .from(user)
                .where(user.nickname.eq(nickname))
                .fetchOne();
        UserDTO userDTO = new UserDTO(tuple.get(user.email), tuple.get(user.name), tuple.get(user.nickname));
        return userDTO;
    }

    @Override
    public boolean regMember(JoinDTO joinDTO) throws Exception {
        try{
            User user = User.builder()
                    .name(joinDTO.getName())
                    .email(joinDTO.getEmail())
                    .password(joinDTO.getPassword())
                    .regtime(joinDTO.getRegtime())
                    .build();
            userRepository.save(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMember(String nickname) throws Exception {
        try{
            User findUser = queryFactory
                    .select(QUser.user)
                    .from(QUser.user)
                    .where(QUser.user.nickname.eq(nickname))
                    .fetchOne();
            userRepository.delete(findUser);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
