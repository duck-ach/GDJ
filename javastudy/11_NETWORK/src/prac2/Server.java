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
		try {
			this.client = client;
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
		
		InetSocketAddress address = null;
		
		try {
			while(true) {
				String message = in.readLine();
				if(message == null || message.equalsIgnoreCase("exit")) {
					break;
				}
				// 모든 클라이언트에게 메시지 출력
				address = (InetSocketAddress)client.getRemoteSocketAddress(); 
				for(Server server : ServerMain.servers) {
					server.sendMessage(address.getHostName() + "의 메시지 : " + message); // 3
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
