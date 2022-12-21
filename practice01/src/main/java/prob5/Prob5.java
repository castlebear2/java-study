package prob5;

public class Prob5 {

	public static void main(String[] args) {

		for (int i = 3; i <= 99; i++) {
			int[] arr = new int[2];
			int ans = 0;
			arr[0] = i / 10;
			arr[1] = i % 10;

			if (arr[0] % 3 == 0 && arr[0] != 0)
				ans++;
			if (arr[1] % 3 == 0 && arr[1] != 0) 
				ans++;
			
			if (ans == 2) {
				System.out.println(arr[0]+""+arr[1]+"짝짝");
			}
			if (ans == 1) {
				arr[0]*=10;
				System.out.println(arr[0]+arr[1]+"짝");	
			}
			
		
		}

}
}
