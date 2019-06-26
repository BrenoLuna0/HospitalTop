package hospital;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ThreadUnicast extends Thread {
	
	Socket socket;
	
	public ThreadUnicast(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			
			while(true) {
				//TODO Criar parte de interagir com o servidor via unicast para realizar a consulta
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
