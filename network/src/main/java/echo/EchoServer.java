package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {

	public static final int PORT = 8000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket();

			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT), 10);
			Log("starts...[port:" + PORT + "]");

			Socket socket = serverSocket.accept();

			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			Log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");

			try {
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
				// bufferedreader로 했으니까, 경계를 알고 있어야해서 개행을 해야한다.!!!
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

				while (true) {
					String data = br.readLine();
					if (data == null) {
						Log("closed by client");
						break;
					}

					Log("received:" + data);
					pw.println(data);
				}
			} catch (SocketException ex) {
				System.out.println("[server] suddenly closed by client");
			} catch (IOException ex) {
				Log("error:" + ex);
			} finally {
				try {
					if (socket != null && !socket.isClosed())
						socket.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

		} catch (IOException e) {
			Log("error:" + e);
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed())
					serverSocket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void Log(String message) {
		System.out.println("[EchoServer] " + message);
	}
}