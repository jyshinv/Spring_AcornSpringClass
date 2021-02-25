package test.cafe;

import org.springframework.stereotype.Component;

@Component
public class StarBucks {
	public void orderOne(Americano a) {
		System.out.println("orderOne()"); //핵심 비즈니스 로직
	}
	public void orderTwo(Latte l, Americano a) {
		System.out.println("orderTwo()"); //핵심 비즈니스 로직
	}
	public void orderThree(Latte l, Americano a, Milk m) {
		System.out.println("orderThree()"); //핵심 비즈니스 로직 
	}
}
