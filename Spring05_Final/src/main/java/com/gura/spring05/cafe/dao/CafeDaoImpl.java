package com.gura.spring05.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring05.cafe.dto.CafeDto;

@Repository
public class CafeDaoImpl implements CafeDao {
	//핵심 의존객체 DI
	@Autowired
	private SqlSession session;
	
	
	@Override
	public void insert(CafeDto dto) {
		// TODO Auto-generated method stub
		session.insert("cafe.insert",dto);
	}

	@Override
	public void update(CafeDto dto) {
		session.update("cafe.update",dto);
	}

	@Override
	public void delete(int num) {
		session.delete("cafe.delete",num);
	}

	@Override
	public CafeDto getData(int num) {
		// TODO Auto-generated method stub
		CafeDto dto=session.selectOne("cafe.getData",num);
		return dto;
	}


	@Override
	public List<CafeDto> getList(CafeDto dto) {
		/*
		parameterType => CafeDto
		parameterType 에는 페이징 처리를 위한 startRowNum과 endRowNum이 들어 있고
		title검색이면 title 필드에 검색 키워드가 들어있다.
		writer검색이면 writer필드에 검색 키워드가 들어있다.
		title+content 검색이면 title and content 필드에 검색 키워드가 들어있다. 
		검색 키워드가 없으면 title, writer, content 필드는 모두 null이다. 
		resultType => CafeDto 
		*/
		List<CafeDto> list=session.selectList("cafe.getList",dto);
		return list;
	}

	@Override
	public int getCount(CafeDto dto) {
		/*
		parameterType=>CafeDto
		parameterType 에는 검색 키워드가 존재한다면 들어있다.
		resultType => int
		*/
		
		int count=session.selectOne("cafe.getCount", dto);
		return count;
	}

	@Override
	public void addViewCount(int num) {
		// TODO Auto-generated method stub
		session.update("cafe.addViewCount",num);
	}

}
