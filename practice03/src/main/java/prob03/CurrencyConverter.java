package prob03;

public class CurrencyConverter {
	private static double rate;
	
	public static void setRate(double rate) {
		CurrencyConverter.rate = rate;
	}
	public static double getRate() {
		return rate;
	}
	public static double toDollar(double krw) {
		return (krw / rate);
	}
	public static double toKrw(double dollar) {
		return (dollar * rate);
	}
	
}
