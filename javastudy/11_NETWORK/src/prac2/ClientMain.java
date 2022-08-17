package prac2;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) {
	
		Socket socket = null;
		Scanner sc = null;
		BufferedWriter out = null;
		
		
		try {
			
			socket = new Socket();
			socket.connect(new InetSocketAddress("localhost", 9090));
			
			sc = new Scanner(System.in);
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			Client client = new Client(socket);
			client.start();
			
			while(true) {
				System.out.print(">>> ");
				String message = sc.nextLine();
				if(message.equalsIgnoreCase("exit")) {
					break;
				}
				out.write(message);
				out.flush(); // flush()는 현재 버퍼에 저장되어 있는 내용을 클라이언트로 전송하고 버퍼를 비운다.
			}

			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null) {
					out.close();
				}
				if(socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
