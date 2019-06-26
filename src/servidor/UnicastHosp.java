package servidor;

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
	
	public String start(String msg) {
		
		try {
			s = new Socket(this.address, this.port);

			//TODO Enviar msg e fazer toda a interação para gerar consulta com mod hospital
			//TODO receber resposta (Consulta)
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
