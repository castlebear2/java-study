package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding) -> (주소를 소켓에다가 붙이는 과정)
			// Socket에 InetSocketaddress(IP Address + Port)를 바인딩 한다.
			// IPAddress: 0.0.0.0: 특정 호스트 IP에 바인딩 하지 않는다.
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000)); // >>>>>>>>>> 이게 서버생성 한 거

			// 3. accept
			Socket socket = serverSocket.accept(); // blocking,

			try {
				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				int remotePort = inetRemoteSocketAddress.getPort();
				System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort + "]");

				// 4. IO Stream 받아오기
				OutputStream os = socket.getOutputStream();
				InputStream is = socket.getInputStream();

				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking
					if (readByteCount == -1) {
						// ? ??? ? ? ? ? ? ? ? /서버가 정상적으로 종료(close() 호출)
						System.out.println("[server] closed by client");
						break;
					}

					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] received:" + data);

					// 6. 데이터 쓰기
					os.write(data.getBytes("utf-8"));

				}
			} catch (SocketException ex) {
				System.out.println("[server] suddenly closed by client");
			} catch (IOException ex) {
				System.out.println("[server] error:" + ex);
			} finally {
				try {
					if (socket != null && !socket.isClosed())
						socket.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

		} catch (IOException e) {
			System.out.println("[server] error:" + e); // 이건 서버에서 나오는 에러다. 라고 하기.
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed())
					serverSocket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
