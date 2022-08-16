package prac2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMain {

	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		
		try {
			
			// serverSocket 생성
			serverSocket = new ServerSocket();
			
			// serverSocket의 호스트/포트번호 설정
			serverSocket.bind(new InetSocketAddress("localhost", 9094));
			
			while(true) {
				
				System.out.println("[서버] 클라이언트 접속 기다리는 중");
				
				// 클라이언트 접속 및 카운팅
				Socket clientSocket = serverSocket.accept();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
