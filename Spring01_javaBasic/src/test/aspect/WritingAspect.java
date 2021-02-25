package test.aspect;
/*
Aspect : 공통 곤심사 (횡단 관심사)
	-핵심 비즈니스 로직과는 상관 없는 공통 관심사를 따로 작성한다. 
*/

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //aspect 역할을 할 수 있도록 필요한 어노테이션 
@Component //component scan을 통해서 bean이 될 수 있도록 
public class WritingAspect {
	
	//write*() 의 의미 write로 시작하는 모든 메소드가 수행되기 이전에 prepare메소드를 호출해라 
	@Before("execution(void write*())")
	public void prepare() {
		System.out.println("pen을 준비해요");
	}
	
	//write*() 의 의미 write로 시작하는 모든 메소드가 수행된 후 prepare메소드를 호출해라 
	@After("execution(void write*())")
	public void finish() {
		System.out.println("pen을 다시 정리해요");
	}
	
	//Before과 After을 합친것이 Around 
	@Around("execution(void *Email(java.lang.String))") //Email로 끝나는 메소드가 수행되기 전후에 메소드를 호출하라 
	public void emailConcern(ProceedingJoinPoint joinPoint) throws Throwable {
		//aspect가 적용된 메소드가 수행되기 이전에 작업할 내용(메소드 수행 이전에 실행한다.)
		System.out.println("이전과 이후는 어떻게 구별하지?");
		System.out.println("이메일 보내기 이전입니다!");
		//aspect가 적용된 메소드 호출해서 수행하기
		joinPoint.proceed();
		//aspect가 적용된 메소드가 리턴된 직후 작업할 내용(리턴된 직후!! 즉, 함수가 종료되고 나서)
		System.out.println("이베일 보낸 이후입니다!");
		System.out.println("웹브라우저를 닫아요!");
	}
	
	@Around("execution(void sendGreet(String))")
	public void greetConcern(ProceedingJoinPoint joinPoint) throws Throwable {
		/*
		WritingUtil.java에 있는 sendGreet(String) 메소드는 
		메소드에 전달되는 인자의 개수는 1개
		메소드에 전달되는 인자의 type은 String
		joinPoint.getArgs()는 Object[] type을 리턴한다.
		Object[] 에 0번방에 전달된 인자가 Object type으로 들어있다. 따라서 String으로 캐스팅 해주어야한다.
		*/
		
		//아래 힌틀를 이용해 바보나 똥깨라는 말이 들어가면 proceed메소드가 호출되지 않도록 만들어보시오
		String str="하나 바보 두울";
		boolean result=str.contains("바보"); //바보가 str안에 포함되어 있다면 true를 반환한다. 
		
		//인자로 전달된 String type의 참조값 얻어내기
		Object[] args=joinPoint.getArgs();
		String greet=(String)args[0];
		
		//만일 인사말에 "바보" 또는 "똥깨"가 포함되어있다면
		if(greet.contains("바보") || greet.contains("똥깨")) {
			return; //메소드를 여기서 종료 (따라서 아래의 .proceed()가 호출되지 않는다.)
		}
		joinPoint.proceed();			
		//System.out.println("greet:"+greet);

	}
	
	@Around("execution(String getGreet())")
	public Object getConcern(ProceedingJoinPoint joinPoint) throws Throwable {
		//aspect 가 적용된 메소드가 리턴한 데이터의 참조값을 받아볼 수 있다. 
		Object obj=joinPoint.proceed();
		//원래 type으로 casting
		String returnedData=(String)obj;
		System.out.println("returnedData:"+returnedData);
		//aspect 에서 조건부로 다른 data를 리턴할 여지도 있다.
		String myData="맛있는 점심 되세요~";
		
		//리턴된 데이터인 obj를 리턴하는 것이 아닌 return obj(x)
		//aspect에서 조건부로 다른 data를 리턴하여 myData에 저장한 후 myData를 리턴할 수도 있다.
		return myData;
		
	}
}
