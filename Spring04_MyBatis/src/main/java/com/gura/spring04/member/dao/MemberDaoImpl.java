package com.gura.spring04.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring04.member.dto.MemberDto;

//component scan을 통해서 bean이 되도록 어노테이션을 붙여준다. dao엔 보통 repository 어노테이션을 붙여준다.
@Repository
public class MemberDaoImpl implements MemberDao{

	/*
		servlet-context.xml 문서에 bean 설정으로 bean인 된 
		Mybatis라이브러리의 SqlSessionTemplate 객체의 참조값을  필드에 주입(DI) 
		@AutoWired 어노테이션을 필드 앞이나 위에 붙여주면 된다. 
		단, MemberDaoImpl 객체도 bean이 되어야한다.
		
		SqlSession객체는 servlet-content.xml에서 bean태그를 통해 직접 bean으로 만들어 관리하고 있고
		이 객체를 Autowired어노테이션을 통해 주입받아 사용하고 있다. 
	*/
	@Autowired
	private SqlSession session;
	
	@Override
	public List<MemberDto> getList() {
		/*
		Mapper.xml 문서의 namespace => member
		sql의 id => getList
		parameterType => 없음
		resultType => MemberDto
		 */
		//mybatis를 사용한다면 코드가 이렇게나 간단해진다. 
		//MemberMapper.xml에서 namespace명.id명으로 사용한다. 
		//.selectList()를 호출했을 때 resultType이 곧 List의 Generic type이 됩니다. 
		List<MemberDto> list=session.selectList("member.getList");
		
		return list;
	}

	@Override
	public void insert(MemberDto dto) {
		/*
		 *  Mapper.xml 문서의 namespace => member
		 *  sql 의 id => insert
		 *  parameterType => MemberDto
		 */
		session.insert("member.insert", dto);
	}

	@Override
	public void update(MemberDto dto) {
		/*
		Mapper.xml 문서의 namespace => member
		sql의 id => update
		parameterType => MemberDto 
		 */
		session.update("member.update",dto);
		
	}

	@Override
	public void delete(int num) {
		/*
		Mapper.xml 문서의 namespace => member
		sql의 id => delete
		parameterType => int  
		 */
		session.delete("member.delete",num);
		
	}

	@Override
	public MemberDto getDate(int num) {
		/*
		Mapper.xml 문서의 namespce => member
		sql의 id=> getData
		parameterType => int
		resultType => MemberDto 
		 */
		//.selectionOne()을 호출했을 때 resultType이 곧 리턴타입이 됩니다.
		MemberDto dto=session.selectOne("member.getData",num);
		return dto;
	}
	

}
