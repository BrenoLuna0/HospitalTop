package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class UnicastHosp {
	private Socket s;
	private String address;
	private int port;

	public UnicastHosp(String ipPorta) {
		String data[] = ipPorta.split(":");
		this.address = data[0];
		this.port = Integer.parseInt(data[1]);
	}
	
	public String start(String nome, String contato) {
	    String result = "";
		
		try {
			s = new Socket(this.address, this.port);

			String msg = nome + "," + contato;

            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            dos.writeUTF(msg);

            dos.flush();

            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            result = dis.readUTF();


			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
