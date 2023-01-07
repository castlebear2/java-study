package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {	
	public static List<Writer> listWriters = new ArrayList<Writer>();
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket();
			serverSocket.bind( new InetSocketAddress( "127.0.0.1", 8000 ) );
			
			while(true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, listWriters).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
