package com.gura.spring01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//1. 컨트롤러 역할을 할 수 있도록 @Controller 어노테이션을 붙인다. 
@Controller
public class FortuneController {
	//2. 이 메소드로 어떤 요청을 처리 할것인지 @RequestMApping 어노테이션에 명시한다. 
	@RequestMapping("/fortune.do")
	public String fortune(HttpServletRequest request) { //리턴 type은 String으로 해야하고 메소드명은 마음대로  지을 수 있다. 
		/*
		4. 메소드 안에서 필요한 객체 (HttpServletRequest, HttpSession ...) 가 있으면 
		메소드의 인자로 선언만 하면 알아서 해당 객체의 참조값이 전달된다. 
		jsp에서 service메소드에서 하던 것을 여기서 한다고 생각하면 된다. 
		*/
		
		//jsp페이지에 전달할 데이터(Model)
		String fortuneToday="동쪽으로 가면 귀인을 만나요";
		
		//request scope에 담는다. fortuneToday라는 키값으로 fortuneToday로 보낸다. 
		//포워드 요청한 jsp페이지에서는 ${requestScope.fortuneToday }로 값을 받아올 수 있다. 
		request.setAttribute("fortuneToday", fortuneToday);
		
		/*
		3. forward 이동할 jsp 페이지의 위치를 문자열로 리턴해준다.
		
		리턴된 문자열의 접두어(prefix)는 "/WEB-INF/views/"가 되고
		리턴된 문자열의 접미어(suffix)는 ".jsp"가 된다.
		따라서 "fortune"을 리턴하면 
		"WEB-INF/views/" + "fortune" + ".jsp" 문자열이 만들어져서
		결과는
		"/WEB-INF/views/fortune.jsp" 가 jsp페이지의 위치가 된다. 
		이 설정은 WEB-INF/spring/appServlet/servlet-context.xml에 정의되어있다. 
		
		그럼으로 해당 위치를 찾아서 jsp 파일을 만들어서 응답하면 된다. 
		*/
		// views폴더 안 fortune.jsp로 foward요청
		return "fortune";
		
		//퀴즈) 만약 return "test/fortune"를 하게 되면 views에 test폴더를 만들고 fortune.jsp를 만들어야한다.
		
	}
}
