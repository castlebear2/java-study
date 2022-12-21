package prob5;

public class Prob5 {

	public static void main(String[] args) {

		for (int i = 3; i <= 99; i++) {
			int[] arr = new int[2];

			arr[0] = i / 10;
			arr[1] = i % 10;

			if ((arr[0] % 3 == 0 && arr[0] !=0 ) || (arr[1] % 3 == 0 && arr[1] !=0)) {
				if (arr[0] == 0) {
					System.out.println(arr[1]+"짝");
				} else {
					System.out.println(arr[0]+""+arr[1]+"짝");
				}
			}
		}

	}
}
