package prob2;

public class Prob2 {
	public static void main(String[] args) {
		for (int j = 1; j <= 10; j++) {

			for (int i = j; i <= j+9; i++) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}
}
