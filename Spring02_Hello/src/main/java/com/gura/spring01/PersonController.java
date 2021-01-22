package com.gura.spring01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*component scan될때 PersonController 객체가 생성이 되고 관리가 된다.*/
@Controller
public class PersonController {
	@RequestMapping("/person.do") //.do는 생략가능
	public String person(HttpServletRequest request) {
		String personToday = "김구라";
		request.setAttribute("personToday", personToday);
		
		//forward 이동할 view page의 위치를 리턴해준다.
		//WEB-INF/views/person.jsp를 리턴함
		//즉, person.jsp로 forward이동해서 응답 
		//(이때 전달하는 데이터를 Model, forward이동할 페이지를 뷰페이지 라고도 한다.)
		return "person"; 
	}
}
