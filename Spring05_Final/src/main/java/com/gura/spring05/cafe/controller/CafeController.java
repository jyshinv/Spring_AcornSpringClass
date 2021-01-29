package com.gura.spring05.cafe.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.cafe.service.CafeService;

@Controller
public class CafeController {
	
	//의존객체 DI
	@Autowired
	private CafeService service;
	
	//글 삭제 요청처리
	@RequestMapping("/cafe/private/delete.do")
	public String delete(int num) {
		service.deleteContent(num);
		return "cafe/private/delete";
	}
	
	//글 수정 요청처리
	@RequestMapping(value="/cafe/private/update.do", method=RequestMethod.POST)
	public String update(@ModelAttribute("dto") CafeDto dto) {
		//update메소드의 매개변수인 CafeDto dto를 forward이동된 jsp페이지에서 사용하고 싶을 때 ModelAttribute("dto")로 지정하면 된다. 
		//그럼 jsp페이지에서 dto.~ 으로 사용할 수 있다. 
		//즉, ModelAttribute("dto")는 ModelAndView에 담거나, request.setParameter로 데이터를 담는 효과가 있다. 
		service.update(dto);
		return "cafe/private/update";
	}
	
	//글 수정 폼 요청처리
	@RequestMapping("/cafe/private/update_form.do")
	public ModelAndView updateform(int num, ModelAndView mView) {
		service.getDatail(num, mView);
		mView.setViewName("cafe/private/update_form");
		return mView;
	}
	
	//글 상세보기 요청 처리
	@RequestMapping("/cafe/detail.do")
	public ModelAndView detail(@RequestParam int num, ModelAndView mView) {
		//자세히 보여줄 글 번호가 파라미터로 넘어온다.
		service.getDatail(num, mView);
		//view page로 forward 이동해서 응답
		mView.setViewName("cafe/detail");
		return mView;
	}
	
	//글 목록 요청처리
	@RequestMapping("/cafe/list.do")
	public ModelAndView list(ModelAndView mView, HttpServletRequest request) {
		service.getList(mView, request);
		mView.setViewName("cafe/list");
		return mView;
	}
	
	//카페 새글 저장 요청 처리
	@RequestMapping(value="/cafe/private/insert.do", method=RequestMethod.POST)
	public String insert(CafeDto dto, HttpSession session) {
		//insert메소드의 매개변수 CafeDto dto로 폼전송된 내용을 받아올 수 있다.
		//insert메소드의 매개변수 HttpSession session으로 sessionScope에 저장된 값을 불러올 수 있따.
		String id=(String)session.getAttribute("id");
		//dto에 담는다.
		dto.setWriter(id);
		//서비스를 통해서 새글을 DB에 저장
		service.saveContent(dto);
		return "cafe/private/insert";
	}
	
	//카페 새글쓰기 요청 처리 
	@RequestMapping("/cafe/private/insertform.do")
	public String insertform() {
		
		// /WEB-INF/views/cafe/private/insertform.jsp 페이지로 forward이동해서 응답하겠다.
		return "cafe/private/insertform";
	}
}
