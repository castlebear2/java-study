package chat.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientApp {
	public static final int PORT = 8000;
	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {
		String name = null;
		Scanner scanner = new Scanner(System.in);

		// 1. create socket
		Socket socket = new Socket();
		PrintWriter pw;
		BufferedReader br;
		try {
			socket.connect(new InetSocketAddress(SERVER_IP, PORT));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			while (true) {
				System.out.println("대화명을 입력하세요.");
				System.out.print(">>> ");
				name = scanner.nextLine();

				if (!name.isEmpty()) {
					break;
				}

				System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
			}
			pw.println("join:"+name);
			
			
			String line = br.readLine();
			
			if ("join:ok".equals(line)) {
				new ChatWindow(name,socket).show();
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			scanner.close();
		}
		// 2. connect to server

		// 3. get iostream
		// 4. join protocol 진행
//		String line = "JOIN:OK";

	}
	
	public static void log(String string) {
		System.out.println("[clientthread] " + string);
	}
}