package com.gura.spring05.gallery.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.gallery.dto.GalleryDto;
import com.gura.spring05.gallery.service.GalleryService;

@Controller
public class GalleryController {
	@Autowired
	private GalleryService service;

	@RequestMapping("/gallery/list")
	public ModelAndView list(ModelAndView mView, HttpServletRequest request) {
		service.getList(mView, request);
		mView.setViewName("gallery/list");
		//mView에는 list, totalPageCount, pageNum등의 정보가 들어있다.  
		return mView;
	}
	
	@RequestMapping("/gallery/ajax_page")
	public ModelAndView ajaxPage(ModelAndView mView, HttpServletRequest request) {
		service.getList(mView, request);
		mView.setViewName("gallery/ajax_page");
		return mView;
	}
	
	//업로드하러가기1 구현을 위한 코드
	@RequestMapping("/gallery/private/upload_form")
	public String uploadForm() {
		return "gallery/private/upload_form";
	}
	
	//업로드하러가기1 구현을 위한 코드
	@RequestMapping(value = "/gallery/private/upload", method = RequestMethod.POST)
	public String upload(GalleryDto dto, HttpServletRequest request) {
		service.saveContent(dto, request);
		return "redirect:/gallery/list.do";
	}
	
	//ajax 업로드 (업로드하러가기2) 구현을 위한 코드
	@RequestMapping("/gallery/private/ajax_form")
	public String ajaxForm() {
		return "gallery/private/ajax_form";
	}
	
	//ajax 업로드 (업로드하러가기2) 구현을 위한 코드 
	@RequestMapping(value = "/gallery/private/ajax_upload", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxUpload(MultipartFile image, HttpServletRequest request){
		//ajax_form에서 src= "${pageContext.request.contextPath}"+data.imagePath;으로 context path까지 포함시켜서 쓰고있기 때문에
		//여기서 별도로 map에 imagePath를  request.getContextPath() 를 포함해서 return 시킬 필요는 없다.
		//업로드된 이미지를 upload 폴더에 저장하고 경로를 리턴 받는다.
		String imagePath=service.saveImage(image, request);
		//저장된 경로를 JSON 문자열로 응답하기 위해 Map 에 담는다.
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("imagePath", imagePath);
		//Map  을 리턴해서 JSON 문자열이 응답되도록 한다. {"imagePath":"/upload/xxx.jpg"} 형식
		return map;
	}
	 
	//ajax업로드 (업로드하러가기2) 구현을 위한 코드 
	@RequestMapping(value = "/gallery/private/insert", method=RequestMethod.POST)
	public String insert(GalleryDto dto, HttpSession session) {
		//dto에는 caption, imagePath정보가 들어있다. 
		service.addContent(dto, session);
		return "redirect:/gallery/list.do";
	}
	
	@RequestMapping("/gallery/detail")
	public ModelAndView detail(@RequestParam int num, ModelAndView mView) {
		service.getDetail(num, mView);
		mView.setViewName("gallery/detail");
		return mView;
	}

}