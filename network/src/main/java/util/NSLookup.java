package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		InetAddress[] inetAddresses = null;

		while (true) {
			String address = sc.nextLine();
			if (address.equals("exit"))
				break;
			try {
				inetAddresses = InetAddress.getAllByName(address);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} finally {
				if (sc == null)
					sc.close();
			}

			System.out.println(inetAddresses[0]);

		}
	}

}
