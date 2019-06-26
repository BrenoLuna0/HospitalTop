package view;

import hospital.ClientMulticast;
import hospital.ServidorUnicast;
import util.Constantes;

public class Main {

	public static void main(String[] args) {

	    /*
	    * Inicia as Threads multicast e serverUnicast do hospital
	    * */

        ClientMulticast clientMulticast = new ClientMulticast(Constantes.MC_HOSP_IP.getValor(), Constantes.MC_HOSP_PORT.getValor());
        clientMulticast.start();

        ServidorUnicast serverUnicast = new ServidorUnicast(Integer.parseInt(Constantes.UC_PORT.getValor()));
        serverUnicast.start();

	}

}
