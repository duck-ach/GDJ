package prac2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerMain {
	
	// Server : 스레드
	// Client : 스레드
	// Server 하나 당 Client 하나 담당
	
	
	// 생성된 Server 목록
	public static List<Server> servers = new ArrayList<>();
	
//	// 모든 Server에 메시지 전송
//	public static void sendMessage(String message) throws IOException { // 2
//		for(Server server : servers) {
//			server.sendMessage(message);
//		}
//	}
	
	public static void main(String[] args) {
		
		ServerSocket server = null;
		Socket client = null;
		
		try {
			
			// serverSocket 생성
			server = new ServerSocket();

			// serverSocket의 호스트/포트번호 설정
			server.bind(new InetSocketAddress("localhost", 9090));
			
			System.out.println("■ 채팅서버오픈 ■");
			
			while(true) {
				
				// 서버 중지 조건은 없는 상태
				
				client = server.accept();
				// 접속한 클라이언트의 ip주소 출력
				System.out.println("클라이언트 접속 (" + client.getInetAddress() + ")");
				
				// 클라이언트를 담당할 서버생성
				Server s = new Server(client);
				s.start();
				
				servers.add(s);
				
				System.out.println("현재 접속 중인 클라이언트 : " + servers.size() + "명");
				
				
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(server.isClosed() == false) {
					server.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}

}
