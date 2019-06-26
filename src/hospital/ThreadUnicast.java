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
				InputStream is = socket.getInputStream();

				DataInputStream dis = new DataInputStream(is);

				String dados = dis.readUTF();

				dados = dados + ", data: 26//6, as 16:00";

				OutputStream os = socket.getOutputStream();

				DataOutputStream dos = new DataOutputStream(os);

				dos.writeUTF(dados);

				dos.flush();

			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
