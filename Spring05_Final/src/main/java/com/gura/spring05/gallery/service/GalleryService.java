package com.gura.spring05.gallery.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.gallery.dto.GalleryDto;

public interface GalleryService {
	public void getList(ModelAndView mView, HttpServletRequest request);
	//image를 save하고 + db에 insert해주는 메소드 (업로드하러가기1)
	public void saveContent(GalleryDto dto, HttpServletRequest request);
	//image를 save하는 메소드 (업로드하러가기2)
	public String saveImage(MultipartFile image, HttpServletRequest request);
	//image를 db에 insert 해주는 메소드 (업로드하러가기2)
	public void addContent(GalleryDto dto, HttpSession session);
	public void getDetail(int num, ModelAndView mView);
	
}