package test.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import test.cafe.Americano;
import test.cafe.Latte;
import test.cafe.Milk;

@Aspect //aspect 역할을 할 수 있도록 필요한 어노테이션 
@Component //component scan을 통해서 bean이 될 수 있도록
public class CafeAspect {
	
	//order*(..)의 의미는 매개변수가 0개~n개인 order로 시작하는 메소드를 의미한다. 
	@Around("execution(void order*(..))")
	public void starbucksConcern(ProceedingJoinPoint joinPoint) throws Throwable {
		//인자로 전달된 객체의 참조값을 Obejct[]로 얻어내기
		Object[] args=joinPoint.getArgs();
		//반복문 돌면서 참조값을 하나씩 불러내서
		for(Object tmp:args) {
			//type을 확인한다.--> order로 시작하는 메소드의 매개변수는 달라지므로 올바르게 casting하기 위해 확인해주어야 한다. 
			if(tmp instanceof Americano) { //instanceof 연산자를 이용해서 type확인
				//Americano type이 맞으면 안전하게 casting할 수 있다.
				Americano a=(Americano)tmp;
				a.drinkAmericano();
			}else if(tmp instanceof Latte) {
				Latte l=(Latte)tmp;
				l.drinkLatte();
			}else if(tmp instanceof Milk) {
				Milk m=(Milk)tmp;
				m.drinkMilk();
			}
		}
		System.out.println("startbucksConcern() 입니다.");
		joinPoint.proceed();
	}
}
