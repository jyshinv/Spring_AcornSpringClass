package test.aop;

import java.applet.AppletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.cafe.Americano;
import test.cafe.Latte;
import test.cafe.Milk;
import test.cafe.StarBucks;
import test.util.WritingUtil;

public class MainClass5 {
	public static void main(String[] args) {
		
		//init.xml 문서를 로딩한다. (spring bean container를 만든다.)
		ApplicationContext context=new ClassPathXmlApplicationContext("test/aop/init.xml");
		
		//spring bean container 에서 StarBucks type 의 참조값 얻어오기
		StarBucks star=context.getBean(StarBucks.class);
		star.orderOne(new Americano());
		star.orderTwo(new Latte(), new Americano());
		star.orderThree(new Latte(), new Americano(), new Milk());
		
		
	}
}
