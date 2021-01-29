package com.gura.spring05.users.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dao.UsersDao;
import com.gura.spring05.users.dto.UsersDto;
import com.gura.spring05.users.service.UsersService;

@Controller
public class UsersController {
	
	//UserService Impl을 의존하지 않고 UserService인터페이스만을 의존하여 의존관계를 느슨하게 하였다.
	//따라서 UserServiceImpl의 파일이름을 바꾸거나 삭제해도 오류가 나지 않는다. 
	@Autowired
	private UsersService service;
	
	//개인정보 수정 요청 처리
	@RequestMapping(value="/users/private/update", method=RequestMethod.POST)
	public ModelAndView update(UsersDto dto, HttpSession session, ModelAndView mView) {
		service.updateUser(dto, session);
		mView.setViewName("users/private/update");
		return mView;
	}
	
	//개인정보 수정 폼 요청처리
	@RequestMapping("/users/private/updateform.do")
	public ModelAndView updateform(ModelAndView mView, HttpSession session) {
		service.getInfo(session, mView);
		mView.setViewName("users/private/updateform");
		return mView;
	}
	
	//프로필 이미지 업로드 요청 처리
	@RequestMapping("/users/private/profile_upload.do")
	public String profile_upload(MultipartFile image, HttpServletRequest request) {
		service.saveProfile(image, request);
		//회원 수정페이지로 다시 리다이렉트 시키기 
		return "redirect:/users/private/updateform.do";
	}
	
	
	//비밀번호 수정 요청 처리
	@RequestMapping("/users/private/pwd_update")
	public ModelAndView pwd_update(ModelAndView mView, UsersDto dto, HttpSession session) {
		//UsersDto에는 폼전송된 아이디, 구비밀번호, 새비밀번호가 담여있다.
		service.updateUserPwd(mView, dto, session);
		mView.setViewName("users/private/pwd_update");
		return mView;
	}
	
	//비밀번호 수정 폼 요청처리
	@RequestMapping("/users/private/pwd_updateform.do")//.do는 생략가능
	public String pwd_updateform() {
		return "users/private/pwd_updateform";
	}
	
	//회원탈퇴 요청차리
	@RequestMapping("users/private/delete.do")//.do는 생략가능
	public String delete(HttpSession session) {
		service.deleteUser(session);
		return "users/private/delete";
	}
	
	//개인정보보기 요청처리
	@RequestMapping("/users/private/info.do")
	public ModelAndView info(ModelAndView mView, HttpSession session) {
		service.getInfo(session, mView);
		mView.setViewName("/users/private/info");
		return mView;
	}
	
	//로그아웃 요청처리(세션에서 아이디/비번을 삭제하면 된다.)
	@RequestMapping("/users/logout.do")//.do는 생략가능
	public String logout(HttpSession session) {
		//session.invalidate(); //세션 초기화
		session.removeAttribute("id");//세션에서 id 삭제 (둘중 하나를 사용한다.)
		return "users/logout"; 
	}
	
	//로그인 요청처리
	@RequestMapping(value="/users/login.do", method=RequestMethod.POST)//.do는 생략가능
	public String login(HttpServletRequest request, HttpServletResponse response) {
		//로그인에 관련된 로직을 서비스를 통해서 처리한다.
		service.loginLogic(request, response);
		
		//view page로 forward이동해서 응답
		return "users/login";
	}
	
	//로그인 폼 요청처리
	@RequestMapping("/users/loginform.do")//.do는 생략가능
	public ModelAndView loginform(HttpServletRequest request, ModelAndView mView) {
		//로그인 폼에 관련된 로직을 서비스를 통해서 처리한다. --> 이러한 뚱뚱한 로직들은 대부분 서비스에서 처리한다.
		service.loginformLogic(request, mView);
		//뷰페이지 정보도 담는다.
		mView.setViewName("users/loginform");
		//리턴
		return mView;
	}
	
	//회원가입 요청처리
	//form 전송은 보통 post방식 요청인데 post방식 요청만 받아들이도록 
	//컨트롤러에 설정하는게 일반적이다. 
	//아래와 같이 requestMapping을 설정해두면
	//get방식 요청을 하면 HTTP 상태 405 – 허용되지 않는 메소드/Request method 'GET' not supported가 뜬다. 
	@RequestMapping(value="/users/signup.do",method = RequestMethod.POST)//.do는 생략가능
	public String signup(@ModelAttribute("dto") UsersDto dto) {
		/*
		 (@ModelAttribute UsersDto dto)는
		  String id = request.getParameter("id");
		  String pwd = request.getParameter("pwd");
		  String email = request.getParameter("email");
		  UsersDto dto = new UsersDto();
		  dto.setId(id);
		  dto.setPwd(pwd);
		  dto.setEmail(email);
		  를 대신해준다. 
		 
		 (@ModelAttribute("dto") UsersDto dto)는
		 Dto인 경우에 @ModelAttribute("key 값") 으로 설정하면
		 해당 Dto가 request영역에 설정한 "key 값"으로 담긴다. 
		 signup.jsp로 forward 이동 후 requestScope.dto.id, requestScope.dto.pwd, requestScope.dto.email로 값에 접근이 가능해진다.  
		  
		*/
		service.addUser(dto);
		return "users/signup";
	}
	
	
	@RequestMapping("/users/signup_form.do")//.do는 생략가능
	public String signupForm() {
		return "users/signup_form";
	}
	
	//ajax 요청처리 --> 이 방식은 checkid.jsp이 있어야한다. 
//	@RequestMapping("/users/checkid.do")//.do는 생략가능
//	public ModelAndView checkid(@RequestParam String inputId,
//			ModelAndView mView) {
//		/*
//		(@RequestParam String inputId)는 
//		String inputId=request.getParameter("inputId");
//		와 같다.
//		*/
//		boolean isExist=service.isExistId(inputId);
//		//ModelAndView 객체에 해당 정보를 담고 view page로 forward 이동해서 응답
//		mView.addObject("isExist",isExist);
//		mView.setViewName("users/checkid");
//		return mView;
//	}
	
	//이 방식은 checkid.jsp가 없어도 된다. (실제로 지워도 동작한다.)
	//위의 ajax요청 처리는 아래와 같이 처리할 수 있다. 
	//ajax 요청 처리 
	@RequestMapping("/users/checkid.do")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam String inputId,
			ModelAndView mView) {
		/*
		 * (@RequestParam String inputId) 
		 * 는
		 * String inputId=request.getParameter("inputId") 
		 * 와 같다.
		 */
		//서비스를 이용해서 해당 아이디가 존재하는지 여부를 알아낸다.
		boolean isExist=service.isExistId(inputId);
		// {"isExist":true} or {"isExist":false} 를 응답하기 위한 Map 구성
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isExist", isExist);
		return map;
	}
}
