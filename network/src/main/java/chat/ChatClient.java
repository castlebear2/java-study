
package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ChatClient {
	public static final int PORT = 8000;
	private static final String SERVER_IP = "127.0.0.1";
	public static String  name =null;
	public static void main(String[] args) {
		Socket socket = null;
		Scanner scanner = null;

		try {
			socket = new Socket();
			
			socket.connect(new InetSocketAddress(SERVER_IP, PORT));
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			scanner = new Scanner(System.in);
			System.out.println("닉네임을 입력하세요:");
			System.out.print(">");
			name = scanner.nextLine();
			pw.println("join" + ":" + name);
			
			ChatClientThread cct = new ChatClientThread(br);
			cct.start();
			   while( true ) {
				      System.out.print( ">>" );
				      String input = scanner.nextLine();
								
				      if( "quit".equals( input ) == true ) {
				          // 8. quit 프로토콜 처리
				    	  pw.println("quit");
				          break;
				      } else {
				          // 9. 메시지 처리    
				    	  pw.println("message:"+input);
				      }
				   }
		} catch(SocketException ex) {
			System.out.println("[client1] suddenly closed by server");
		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();
				}
				if(scanner != null) {
					scanner.close();
				}
			} catch (IOException e) {
				log("error2 : "+e);
			}
		}
	}
	public static void log(String string) {
		System.out.println("[client] "+string);
	}
}
