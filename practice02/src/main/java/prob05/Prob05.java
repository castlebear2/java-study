package prob05;

import java.util.Random;
import java.util.Scanner;

public class Prob05 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// 정답 램덤하게 만들기
		Random random = new Random();
		int correctNumber = random.nextInt(100) + 1;
		while (true) {

			/* 게임 작성 */

			// 새 게임 여부 확인하기
			System.out.println("맞춰보세요 한 번");
			int guessNum = scanner.nextInt();
			if (guessNum == correctNumber) {
				System.out.println("맞췄습니다!!!");
				break;
			} else {
				if (guessNum > correctNumber) {
					System.out.println("DOWN");
				}
				else {
					System.out.println("UP");
				}
			}
			System.out.print("다시 하겠습니까(y/n)>>");
			String answer = scanner.next();
			if ("y".equals(answer) == false) {
				break;
			}
		}

		scanner.close();
	}

}