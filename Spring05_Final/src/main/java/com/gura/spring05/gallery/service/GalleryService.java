package com.gura.spring05.gallery.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.gallery.dto.GalleryDto;

public interface GalleryService {
	public void getList(ModelAndView mView, HttpServletRequest request);
	public void saveContent(GalleryDto dto, HttpServletRequest request);
}