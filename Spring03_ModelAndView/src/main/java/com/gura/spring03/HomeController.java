package com.gura.spring03;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/home") //.do는 생략가능함 생략한 상태임
	public String home(HttpServletRequest request) {
		//DB에서 불러온 공지사항 목록이라고 가정하자.
		List<String> noticeList=new ArrayList<String>();
		noticeList.add("불금이네요");
		noticeList.add("신나게 놀아요");
		noticeList.add("어쩌고");
		noticeList.add("저쩌고");
		//Model(데이터)를 request scope에 담는다. 첫번째 인자는 키값을 전달, 두번째 인자의 데이터 전달, noticeList 타입은  type(List<String>)
		request.setAttribute("noticeList", noticeList);
		//view page로 forward 이동해서 응답하기
		return "home";
	}
	
}
