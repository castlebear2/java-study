package prob02;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];

		// 상품 입력
		String buffer;
		String drink;
		int price;
		int num;
		for(int i=0; i<COUNT_GOODS; i++) {
			System.out.println("음료, 가격, 수량을 입력하세요");
			if(i!=0) {
				buffer = scanner.nextLine();
			}
			
			System.out.print("음료: ");
			drink = scanner.nextLine();
			
			System.out.print("가격: ");
			price = scanner.nextInt();
			
			System.out.print("수량: ");
			num = scanner.nextInt();
			
			
			System.out.println();
			goods[i] = new Goods(drink,price, num);
		}
		// 상품 출력
		for(int i=0 ; i<COUNT_GOODS; i++) {
			System.out.println(goods[i].getDrink() + " " + goods[i].getPrice() + " " + goods[i].getNum());
		}
		// 자원정리
		scanner.close();
	}
}
