package com.gura.spring05.users.dao;

import com.gura.spring05.users.dto.UsersDto;

public interface UsersDao {
	//프로필 이미지 경로 수정
	public void updateProfile(UsersDto dto);
	//아이디 존재 여부 리턴
	public boolean isExist(String id);
	//비밀번호 수정
	public boolean updatePwd(UsersDto dto);
	//회원가입 정보 수정 반영
	public void update(UsersDto dto);
	//회원가입 정보 삭제
	public void delete(String id);
	//가입정보 리턴
	public UsersDto getData(String id);
	//인자로 전달된 정보가 유효한 정보인지 리턴(암호화 복호화 배우고 나서 필요없어졌음!!)
	//public boolean isValid(UsersDto dto);
	
	//인자로 전달된 아이디에 해당하는 비밀번호를 리턴하는 메소드
	public String getPwd(String id);
	
	//회원정보를 저장
	public void insert(UsersDto dto);
}
