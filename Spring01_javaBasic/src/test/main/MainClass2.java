package test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.mypac.Weapon;

public class MainClass2 {
	public static void main(String[] args) {
		/*
			MainClass2.java의 경우 MainClass.java처럼 WeaponImpl객체를 직접 new로 생성하지 않고
			Weapon인터페이스타입으로  WeaponImpl 객체의 참조값을 Spring bean container로부터 받아 사용가능하다 즉, WeaponImpl.java에 의존하지 않는다.
			WeaponImpl과 GoodWeapon은 둘다 Weapon인터페이스를 implements하고 있고 WeaponImpl의 attack()보다 GooWeapon()이 더 강하다.
			WeaponImpl이 아닌  GoodWeapon 사용하여 강력한 무기인 attack()을 사용하고 싶다면
			MainClass.java 의 경우에는 GoodWeapon객체를 new로 생성해  GoodWeapon클래스에 의존해야한 구현이 가능하지만
			MainClass2의 경우  init.xml에 <bean id="myGoodWeapon" class="test.mypac.GoodWeapon"/>를 추가해주고
			new로 생성하는 것 없이 Weapon인터페이스타입으로 GoodWeapon객체의 참조값을 저장하여 GoodWeapon클래스 의존없이 구현이 가능하다.
		*/
		
		
		//pom.xml에 spring framework 라이브러리를 설정해놓았기 때문에 ApplicationContext 사용가능하다.
		//init.xml에 <bean id="myWeapon" class="test.mypac.WeaponImpl"/> 코드 참고하기
		ApplicationContext context=
				new ClassPathXmlApplicationContext("test/main/init.xml");
		/*
		Spring 이 관리하는 객체 중에 id가 myWeapon인 객체의 참조값을
		Object type으로 리턴받아서
		Weapon 인터페이스 type으로 casting해서 w라는 이름의 지역변수에 참조값을 담기 
		(항상 인터페이스 타입으로 값을 받자. WeaponImpl클래스로 받게되면 WeaponImpl에 의존하게 된다.)
		*/
		Weapon w=(Weapon)context.getBean("myWeapon");
		Weapon gw=(Weapon)context.getBean("myGoodWeapon");
		//메소드 호출해서 원하는 목적 달성하기
		w.attack();
		gw.attack();
	}
}
