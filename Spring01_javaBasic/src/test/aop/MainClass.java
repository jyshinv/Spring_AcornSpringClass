package test.aop;

import java.applet.AppletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.util.WritingUtil;

public class MainClass {
	public static void main(String[] args) {
		
		//init.xml 문서를 로딩한다. (spring bean container를 만드는 것과 같은 효과이다.)
		ApplicationContext context=new ClassPathXmlApplicationContext("test/aop/init.xml");
		
		//spring bean container 에서 writingUtil type 의 참조값 얻어오기
		WritingUtil util=context.getBean(WritingUtil.class);
		
		
		util.write1();
		util.write2();
		util.write3();
		
		
		
		
	}
}
