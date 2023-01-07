package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatClientThread extends Thread {
	private BufferedReader bufferedReader;
	public ChatClientThread(BufferedReader bufferedReader) {
		this.bufferedReader  = bufferedReader; 
	}

	@Override
	public void run() {
		String text = null;
		try {
			while(true) {
				text = bufferedReader.readLine();
				if (text == null) {
					break;
				} else {
					System.out.println(text);
				}
			}
			
		} catch (IOException e) {
			System.out.println(ChatClient.name+"님이 나갔습니다");
//			log("error 3: " + e);
		}
	}

	public static void log(String string) {
		System.out.println("[clientthread] " + string);
	}
}
