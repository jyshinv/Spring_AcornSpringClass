package test.aop;

import java.applet.AppletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.util.WritingUtil;

public class MainClass3 {
	public static void main(String[] args) {
		
		//init.xml 문서를 로딩한다. (spring bean container를 만든다.)
		ApplicationContext context=new ClassPathXmlApplicationContext("test/aop/init.xml");
		
		//spring bean container 에서 writingUtil type 의 참조값 얻어오기
		WritingUtil util=context.getBean(WritingUtil.class);
		
		util.sendGreet("안녕하세요");
		util.sendGreet("좋은 아침 입니다.");
		util.sendGreet("안녕 바보야~");
		util.sendGreet("안녕 똥깨야");
		

		
		System.out.println("메인메소드가 종료됩니다.");
	}
}
