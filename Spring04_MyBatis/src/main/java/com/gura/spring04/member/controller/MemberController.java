package com.gura.spring04.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.member.dao.MemberDao;
import com.gura.spring04.member.dto.MemberDto;

@Controller
public class MemberController {
	//MemberDao 인터페이스 type을 주입(DI) 받아서 사용한다.
	/*
	MemberDao클래스는 repository 어노테이션을 통해 빈으로 관리되고 있고
	그것을 Autowired어노테이션을 통해 주입받아서 사용한다. 
	*/ 
	@Autowired
	private MemberDao dao;
	
	@RequestMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		//1. 회원목록을 얻어온다.
		List<MemberDto> list=dao.getList();
		
		//2. 회원목록을 request scope에 담고 view page로 forward 이동해서 응답
		mView.addObject("list",list);
		mView.addObject("member/list");
		
		return mView;
	}
	
	@RequestMapping("/member/insertform")
	public String insertform() {
		return "member/insertform";
	}
	
	@RequestMapping("/member/insert")
	public String insert(HttpServletRequest request) {
		String name=request.getParameter("name");
		String addr=request.getParameter("addr");
		
		MemberDto dto=new MemberDto();
		dto.setName(name);
		dto.setAddr(addr);
		
		//1.회원을 추가한다.
		dao.insert(dto);
		
		return "member/insert";
	}
	
	@RequestMapping("/member/deleteform")
	public String delete(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		//num번인 회원을 삭제한다.
		dao.delete(num);
	
		
		return "redirect:/member/list.do";
	}
	
	//list.jsp(num을 넘김)--->updateform.do(num을 받아서 num과,name과 addr을 넘김) --> update.jsp(num,name,addr을 받아서 삭제처리) --> update.do(결과를 출력)
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(ModelAndView mView, HttpServletRequest request) {		
		int num = Integer.parseInt(request.getParameter("num"));
		mView.addObject("num", num);
		mView.addObject("member/update");
		return mView;
	}
	
	@RequestMapping("/member/update")
	public String update(HttpServletRequest request) {		
		int num = Integer.parseInt(request.getParameter("num"));
		String name=request.getParameter("name");
		String addr=request.getParameter("addr");
		MemberDto dto=new MemberDto();
		dto.setNum(num);
		dto.setName(name);
		dto.setAddr(addr);
		dao.update(dto);
		
		return "member/update";
	}
}
