package hospital;

import util.Constantes;
import util.Infos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;


public class ThreadConn extends Thread {
	protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];
    DatagramPacket packet = null;

    public ThreadConn(MulticastSocket multicastSocket, DatagramPacket packet) {
    	this.socket = multicastSocket;
    	this.packet = packet;
    }
 
    public void run() {
    	try {
            //O primeiro char indica quem enviou o pacote (S = Servidor / H = Hospital)
            if(new String(packet.getData()).charAt(0) == 'S'){
                System.out.println("recebido >> ("+ packet.getAddress() + ":" + packet.getPort() + ") : "+ new String(packet.getData()).substring(1).trim());
                if (verificarEspecialidade(new String(packet.getData()).substring(1).trim())){
                    String ipPortaUnicast = "H"+Constantes.UC_IP.getValor()+":"+Constantes.UC_PORT.getValor();

                    DatagramPacket resposta = new DatagramPacket(ipPortaUnicast.getBytes(), ipPortaUnicast.length(), packet.getAddress(), packet.getPort());

                    try {
                        this.socket.send(resposta);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Respondido IP unicast ao servidor");
                }
            }
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    /**
     * Verifica se o hospital atende a especialidade específica
     **/
    private boolean verificarEspecialidade(String especialidade){
        return Infos.HOSPITAL.atendeEspecialidade(especialidade);
    }
}
