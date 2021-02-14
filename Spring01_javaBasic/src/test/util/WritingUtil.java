package test.util;

import org.springframework.stereotype.Component;

//아래 클래스를 bean으로 만들어보자.
//어노테이션 Component만들어 놓고 Component scan이 되게 만든다면 annotation을 할 수 있음!
@Component
public class WritingUtil {

	public void write1() {
		//System.out.println("펜을 준비해요");//cross concern (핵심 비즈니스 로직과 상관없는 로직(횡단 관심사))
		System.out.println("편지를 써요"); //core logic (핵심 비즈니스 로직)
	}
	
	public void write2() {
		//System.out.println("펜을 준비해요");//cross concern (핵심 비즈니스 로직과 상관없는 로직(횡단 관심사))
		System.out.println("보고서를 써요");//core logic (핵심 비즈니스 로직)
	}
	
	public void write3() {
		//System.out.println("펜을 준비해요");//cross concern (핵심 비즈니스 로직과 상관없는 로직(횡단 관심사))
		System.out.println("일기를 써요");//core logic (핵심 비즈니스 로직)
	}
	  
	public void sendEmail(String address) {
		System.out.println(address+"를 이메일로 보내요!"); //핵심 비즈니스 로직
	}
	
	public void sendGreet(String greet) {
		System.out.println(greet+"라는 인사말을 보내요!"); //핵심 비즈니스 로직
	}
	
	public String getGreet() {
		return "맛점하세요"; 
	}
	
	
}
