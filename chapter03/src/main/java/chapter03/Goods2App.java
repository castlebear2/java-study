package chapter03;

import mypackage.Goods2;

public class Goods2App {

	public static void main(String[] args) {
		Goods2 g = new Goods2();
		
		// public은 제한이 없는 것을 알 수 있다.
		g.name = "camera";
		
		// protected 같은 패키지에서 접근이 가능하다.
		// 더 중요한 것은 자식에서 접근이 가능하다.
		// g.price = 10000;
		
		// default는 같은 패키지에서 접근이 가능하다.
		// g.countStock = 10;
		
		// private는 클래스 안에서만 접근이 가능하다. 그래서 여기선 접근 불가.
		//g.countSold = 50;
		
		

	}

}
