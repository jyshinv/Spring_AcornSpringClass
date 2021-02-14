package com.gura.spring05.gallery.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public interface GalleryService {
	public void getList(ModelAndView mView, HttpServletRequest request);
}