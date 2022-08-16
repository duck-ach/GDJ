package ex03_socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientMain {
		// 상담사와의 실시간 채팅 등 구현할때 쓰인다. 
	
	public static void main(String[] args) {
		
		Socket clientSocket = null;
		try {
			
			// Socket 생성
			clientSocket = new Socket();
			
			// 접속할 Server의 InetSocketAddress 생성
			InetSocketAddress address = new InetSocketAddress("localhost", 9090);
			
			// 서버에 접속
			clientSocket.connect(address);
			
			System.out.println("[클라이언트] 접속 성공");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(clientSocket.isClosed() == false) {
					clientSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
