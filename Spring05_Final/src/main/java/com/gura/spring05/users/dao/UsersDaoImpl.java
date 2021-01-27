package com.gura.spring05.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring05.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao {
	
	//핵심 의존객체 (DI)
	@Autowired
	private SqlSession session;
	
	@Override
	public void updateProfile(UsersDto dto) {
		// 매퍼의 namespace.id 와 parameterType을 인자로 보내주기 
		/*
		mapper namespace => users
		sql id => updateProfile
		parameterType => UsersDto 
		*/
		session.update("users.updateProfile",dto);
	}

	@Override
	public boolean isExist(String id) {
		/*
		mapper namespace => users
		sql id => isExist
		parameterType => id
		resultType=>UsersDt
		 */
		//아이디가 존재하면(이미 등록된 아이디) null이 아니고 존재하지 않으면 null이다.
		UsersDto dto=session.selectOne("users.isExist",id);
		if(dto==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean updatePwd(UsersDto dto) {
		// TODO Auto-generated method stub
		/*
		mapper namespace => users
		sql id => updatePwd
		parameterType => UsersDto 
		 */
		//영향을 받은 row의 개수를 리턴해준다.
		int count=session.update("users.updatePwd",dto);
		//영향받은 row의 개수가 0개이면 비밀번호 변경 실패
		if(count==0) {
			return false;
		}else {//그렇지 않으면 성공
			return true;
		}
	}

	@Override
	public void update(UsersDto dto) {
		// TODO Auto-generated method stub
		/*
		mapper namespace => users
		sql id => update
		parameterType => UsersDto 
		 */
		session.update("users.update",dto);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		/*
		mapper namespace => users
		sql id => delete
		parameterType => String
		 */
		session.delete("users.delete",id);
	}

	@Override
	public UsersDto getData(String id) {
		// TODO Auto-generated method stub
		/*
		mapper namespace => users
		sql id => getData
		parameterType => String
		resultType => UsersDto
		 */
		UsersDto dto=session.selectOne("users.getData",id);
		return dto;
	}

	@Override
	public boolean isValid(UsersDto dto) {
		/*
		mapper namespace => users
		sql id => isValid
		parameterType => UsersDto
		resultType => String
		 */
		String id=session.selectOne("users.isValid",dto);
		if(id==null) { //잘못된 아이디와 비밀번호
			return false; 
		}else { //유효한 아이디와 비밀먼호
			return true;
		}
	}

	@Override
	public void insert(UsersDto dto) {
		// TODO Auto-generated method stub
		/*
		mapper namespace => users
		sql id => insert
		parameterType => UsersDto
		 */
		session.insert("users.insert",dto);
		
	}

}
