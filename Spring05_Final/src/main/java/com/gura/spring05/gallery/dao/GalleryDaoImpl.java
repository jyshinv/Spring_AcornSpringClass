package com.gura.spring05.gallery.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gura.spring05.gallery.dto.GalleryDto;

@Repository
public class GalleryDaoImpl implements GalleryDao{
	//Spring bean container로부터 주입이 된다. 
	@Autowired
	private SqlSession session;

	@Override
	public GalleryDto getData(int num) {
		//GalleryDto dto=session.selectOne("gallery.getData", num);
		//return dto;
		//위의 두줄을 아래 한줄로 쓸 수 있다. 
		return session.selectOne("gallery.getData", num);
	}

	@Override
	public void insert(GalleryDto dto) {
		session.insert("gallery.insert", dto);
	}

	@Override
	public List<GalleryDto> getList(GalleryDto dto) {

		return session.selectList("gallery.getList", dto);
	}

	@Override
	public int getCount() {

		return session.selectOne("gallery.getCount");
	}

}