package com.gura.spring05.cafe.dao;

import java.util.List;

import com.gura.spring05.cafe.dto.CafeDto;

public interface CafeDao {
	//글 추가
	public void insert(CafeDto dto);
	//글 수정
	public void update(CafeDto dto);
	//글 삭제
	public void delete(int num);
	//글 하나의 정보 얻어오기
	public CafeDto getData(int num);
	//글목록 얻어오기(페이징 처리와 검색 키워드를 고려한 목록)
	public List<CafeDto> getList(CafeDto dto);
	//글의 개수 얻어오기(검색 키워드에 해당하는 개수)
	public int getCount(CafeDto dto);
	//글 조회수 올리기 
	public void addViewCount(int num);
}
