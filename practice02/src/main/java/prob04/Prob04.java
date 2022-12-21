package prob04;
public class Prob04 {

	public static void main(String[] args) {
		char[] c1 = reverse( "Hello World" );
		printCharArray( c1 );
		
		char[] c2 = reverse( "Java Programming!" );
		printCharArray( c2 );
	}
	
	public static char[] reverse(String str) {
		/* 코드를 완성합니다 */
		int length = str.length();
		char[] c = new char[length];
		for(int i=str.length()-1; i>=0; i--) {
			c[str.length()-1-i] = str.charAt(i);
		}
		return c;
	}

	public static void printCharArray(char[] array){
		/* 코드를 완성합니다 */
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i]);
		}
		
	}
}