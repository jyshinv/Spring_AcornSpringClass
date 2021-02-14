package test.aop;

import java.applet.AppletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.util.WritingUtil;

public class MainClass4 {
	public static void main(String[] args) {
		
		//init.xml 문서를 로딩한다. (spring bean container를 만든다.)
		ApplicationContext context=new ClassPathXmlApplicationContext("test/aop/init.xml");
		
		//spring bean container 에서 writingUtil type 의 참조값 얻어오기
		WritingUtil util=context.getBean(WritingUtil.class);
		
		//핵심 비즈니스 로직
		String result=util.getGreet();
		System.out.println("result:"+result);
		
		System.out.println("메인메소드가 종료됩니다.");
	}
}
