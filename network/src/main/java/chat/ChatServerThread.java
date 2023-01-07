package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ChatServerThread extends Thread {
	private Socket socket;
	private String nickname;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	List<Writer> listWriters;

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
		printWriter = null;
		bufferedReader = null;
	}

	@Override
	public void run() {
//		InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
//		String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
//		int remotePort = inetRemoteSocketAddress.getPort();
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),
					true);
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

			while (true) {
				String request = bufferedReader.readLine();
				if (request == null) {
					log("클라이언트로 부터 연결 끊김");
					break;
				}

				// 4. 프로토콜 분석
				String[] tokens = request.split( ":" );
				if( "join".equals( tokens[0] ) ) {
				   doJoin( tokens[1], printWriter );
				} else if( "message".equals( tokens[0] ) ) {
				   doMessage( tokens[1] );
				} else if( "quit".equals( tokens[0] ) ) {
				   doQuit(printWriter);
				} else {
				   log( "에러:알수 없는 요청(" + tokens[0] + ")" );
				}
			}
		} catch (

		SocketException ex) {
			System.out.println("[server] suddenly closed by client");
		} catch (IOException ex) {
			System.out.println(ex);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

private void doMessage(String string) {
		broadcast(nickname+":"+string);
	}

private void log(String string) {
		System.out.println("[chat server]"+string);
	}

	//	public void doJoin(String nickname, Writer writer) {
//		
//		this.nickname = nickname;
//		
//		synchronized(listWriters) {
//			for (Writer writers : listWriters) {
//				PrintWriter printWriter = (PrintWriter)writers;
//				printWriter.println(nickname+"이(가) 입장하셨습니다" );
//			}
//			listWriters.add(writer);
//		}
//		
//	}
	// ppt 자료
	private void doJoin(String nickName, Writer writer) {
		this.nickname = nickName;

		String data = nickName + "님이 참여하였습니다.";
		broadcast(data);

		/* writer pool에 저장 */
		addWriter(writer);

		// ack
		printWriter.println("join:ok");
		printWriter.flush();
		System.out.println("join:ok");
	}

	private void doQuit(Writer writer) {
		removeWriter(writer);

		String data = nickname + "님이 퇴장 하였습니다.";
		broadcast(data);
	}

	private void removeWriter(Writer writer) {
		listWriters.remove(listWriters.indexOf(writer));
	}

	private void broadcast(String data) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}
}
