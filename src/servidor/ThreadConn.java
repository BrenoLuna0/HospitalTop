package servidor;

import util.*;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import classesBase.Paciente;
import conexaoMongo.ConPaciente;

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
            //Tratar se é inserção ou gerarConsulta
            String msg = new String(packet.getData(), 0, packet.getLength());
            msg = msg.substring(1, msg.length()-1);
            char op = msg.charAt(0);
            msg = msg.substring(1);

            System.out.println("Msg Recebida: "+msg);
            //O primeiro char indica a operação (I = Inserir Paciente / C = Gerar consulta

            switch (op){
                case 'i':   inserirPaciente(msg);
                    break;
                case 'c':   gerarConsulta(msg);
                    break;
                default:
                    break;
            }

    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    private void gerarConsulta(String msg) {
        System.out.println("Gerar consulta");
        //msg => "cpf,especialidade"
        String[] infos = msg.split(",");

        //Buscar paciente pelo CPF
        Paciente paciente = new ConPaciente().buscarPorCpf(infos[0]);
        if (paciente == null){
            System.out.println("Paciente não encontrado (CPF: "+infos[0]+")");
            return;
        }

        //Buscar hospital disponível para atender a especialidade
        ServerMulticastHosp serverHosp = new ServerMulticastHosp(Constantes.MC_HOSP_IP.getValor(), Constantes.MC_HOSP_PORT.getValor());
        //'S' identifica que o servidor enviou a msg
        serverHosp.enviarMsg("S" + infos[1]);
        String ipPortaHospital = serverHosp.aguardarHospital(); //TODO fica aguardando indeterminado, colocar um timeout?

        if (ipPortaHospital.isEmpty()){
            System.out.println("Hospital não encontrado (Especialidade: "+infos[1]+")");
            return;
        }

        //Conectar a hospital e gerar consulta
        UnicastHosp unicastHosp = new UnicastHosp(ipPortaHospital);
        String consulta = unicastHosp.start(paciente.getNome(), paciente.getContato());

        //TODO Imprimir informações da consulta
        System.out.println(consulta);
    }

    private void inserirPaciente(String info){
        System.out.println("Chegou no inserir");
        Paciente p = MontarString.montarPaciente(info);
        new ConPaciente().inserir(p);
    }
}
