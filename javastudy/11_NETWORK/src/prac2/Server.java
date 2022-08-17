package prac2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Server extends Thread {

	private Socket client;
	private BufferedReader in;
	private BufferedWriter out;
	
	
	public Server(Socket client) {
		this.client = client;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) throws IOException { // 1
		out.write(message);
		out.flush();
		
	}
	
	
	@Override
	public void run() {
		try {
			InetSocketAddress address = null;
			String message = null;
			while(true) {
				message = in.readLine();
				if(message.equalsIgnoreCase("exit")) {
//					ServerMain.servers.remove(this);
					break;
				}
				// 모든 클라이언트에게 메시지 출력
				address = (InetSocketAddress)client.getRemoteSocketAddress(); 
				ServerMain.sendMessage(address.getHostName() + "의 메시지 : " + message); // 3
			}
			
			// List<Server> servers 에서 등록된 서버 제거
			// this = 현재 실행중인 서버
			ServerMain.servers.remove(this);
			System.out.println(address.getHostName() + " 채팅 종료");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
