package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Member;

@Mapper
public interface MemberMapper {
	public Member login(Member member) throws SQLException;
	public Member info(String nickname) throws SQLException;
	public int regMember(Member member) throws SQLException;
	public int modifyMember(Member member) throws SQLException;
	public int deleteMember(String nickname) throws SQLException;

	
}