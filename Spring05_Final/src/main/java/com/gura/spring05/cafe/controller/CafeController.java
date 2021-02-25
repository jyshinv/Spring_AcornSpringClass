package com.gura.spring05.cafe.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.cafe.dto.CafeCommentDto;
import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.cafe.service.CafeService;

@Controller
public class CafeController {
	
	//의존객체 DI
	@Autowired
	private CafeService service;
	
	//새 댓글 저장 요청 처리
	@RequestMapping(value="/cafe/private/comment_insert", method=RequestMethod.POST)
	public String commentInsert(HttpServletRequest request,
			@RequestParam int ref_group) {
		//새 댓글을 저장하고
		service.saveComment(request);
		//글 자세히 보기로 다시 리다일렉트 이동 시킨다.
		//ref_group 은 자세히 보기 했던 글번호 
		return "redirect:/cafe/detail.do?num="+ref_group;
	}
	
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
	
	//댓글 삭제 기능
	@RequestMapping("/cafe/private/comment_delete")
	public ModelAndView commentDelete(HttpServletRequest request,
			ModelAndView mView, @RequestParam int ref_group) {
		service.deleteComment(request);
		mView.setViewName("redirect:/cafe/detail.do?num="+ref_group);
		return mView;
	}
	
	//댓글 수정 ajax 요청에 대한 요청 처리
	@RequestMapping(value = "/cafe/private/comment_update", 
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> commentUpdate(CafeCommentDto dto){
		
		
		//핵심 비즈니스 로직과 상관없는 코드1
		long startTime=System.currentTimeMillis(); //시작시간
		
		//핵심 비즈니스 로직
		//댓글을 수정 반영하고 
		service.updateComment(dto);
		//JSON 문자열을 클라이언트에게 응답한다.
		Map<String, Object> map=new HashMap<>();
		map.put("num", dto.getNum());
		map.put("content", dto.getContent());
		
		//핵심 비즈니스 로직과 상관없는 코드2
		long endTime=System.currentTimeMillis(); //시작시간
		long time=endTime-startTime; //소요시간(처리하는데 소요된 시간을 의미)
		System.out.println("소요시간:"+time+" 입니다.");//처리하는데 걸린 시간 출력
		
		//핵심 비즈니스 로직 
		return map;
	}
	
	@RequestMapping("/cafe/ajax_comment_list")
	public ModelAndView ajaxCommentList(HttpServletRequest request,
			ModelAndView mView) {
		service.moreCommentList(request);
		mView.setViewName("cafe/ajax_comment_list");
		return mView;
	}
}
