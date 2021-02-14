package com.gura.spring05.gallery.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.gallery.service.GalleryService;

@Controller
public class GalleryController {
	@Autowired
	private GalleryService service;

	@RequestMapping("/gallery/list")
	public ModelAndView list(ModelAndView mView, HttpServletRequest request) {
		service.getList(mView, request);
		mView.setViewName("gallery/list");
		return mView;
	}
	
	@RequestMapping("/gallery/ajax_page")
	public ModelAndView ajaxPage(ModelAndView mView, HttpServletRequest request) {
		service.getList(mView, request);
		mView.setViewName("gallery/ajax_page");
		return mView;
	}
	
	@RequestMapping("/gallery/private/upload_form")
	public String uploadForm() {
		return "gallery/private/upload_form";
	}

}