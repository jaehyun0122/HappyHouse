package com.ssafy.happyhouse.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Member login(Member member) throws Exception{
		if(member.getNickname() == null || member.getPassword() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(member);
	}
	
	@Override
	public Member info(String nickname) throws Exception{
	
		return sqlSession.getMapper(MemberMapper.class).info(nickname);
	}
	
	@Override
	public boolean regMember(Member member) throws Exception{
		if(member.getNickname() == null || member.getName() == null || member.getPassword() == null || member.getEmail() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(MemberMapper.class).regMember(member) == 1;
	}
	
	@Override
	@Transactional
	public boolean modifyMember(Member member) throws Exception{
	
		return sqlSession.getMapper(MemberMapper.class).modifyMember(member) == 1;
	}
	
	@Override
	@Transactional
	public boolean deleteMember(String nickname) throws Exception{
	
		return sqlSession.getMapper(MemberMapper.class).deleteMember(nickname) == 1;
	}

	
}
