package prob04;

public class StringUtil {
	public static String concatenate(String[] str) {
		String ans = "";
		for(int i=0; i<str.length; i++) {
			ans += str[i];
		}
		return ans;
	}
}
