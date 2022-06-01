package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.Member;

public interface MemberService {
	public Member login(Member member) throws Exception;
	public Member info(String nickname) throws Exception;
	public boolean regMember(Member member) throws Exception;
	public boolean modifyMember(Member member) throws Exception;
	public boolean deleteMember(String nickname) throws Exception;

}
