package prac2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) {
	
		Socket clientSocket = null;

		try {
			
			clientSocket = new Socket();
			
			clientSocket.connect(new InetSocketAddress("localhost", 9094));
			
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			Scanner sc = new Scanner(System.in);
			String str1 = sc.next();
			byte[] b = null;
			
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
