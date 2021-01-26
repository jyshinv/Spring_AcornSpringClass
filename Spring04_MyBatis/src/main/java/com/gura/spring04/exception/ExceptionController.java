package com.gura.spring04.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
예외가 발생했을 때 직접 에러페이지를 응답하려면 예외 컨트롤러를 정의해서 응답할수 있다.
예외 컨트롤러를 만들 때는 @ControllerAdvice 어노테이션을 붙여서 만들면 된다. 
*/
@ControllerAdvice
public class ExceptionController {
	/*
	스프링 프레임워크가 동작하는 중에 DBFailException type의 예외가 
	발생하면 호출되는 메소드 
	@ExceptionHandler(예외 클래스 type)
	*/
	@ExceptionHandler(DBFailException.class)
	public ModelAndView dbFail(DBFailException e) { //발생한 예외 객체가 전달된다. 
		//ModelAndView 객체를 생성해서 
		ModelAndView mView=new ModelAndView();
		//exception이라는 키값으로 예외 객체를 담고
		mView.addObject("exception",e);
		//view page로 forward 이동해서 에러 정보를 응답한다. 
		mView.setViewName("error/db_fail");
		return mView;
	}
}
