package com.gura.spring04.member.dao;

import java.util.List;

import com.gura.spring04.member.dto.MemberDto;

public interface MemberDao {
	//회원목록을 리턴하는 메소드
	public List<MemberDto> getList(); //인터페이스이므로 메소드의 형태만 지정해준다.
	//회원정보를 추가하는 메소드 
	public void insert(MemberDto dto);
	//회원정보를 수정하는 메소드
	public void update(MemberDto dto);
	//회원정보를 삭제하는 메소드
	public void delete(int num);
	//회원 한명의 정보를 리턴하는 메소드
	public MemberDto getData(int num);
	
	
}
