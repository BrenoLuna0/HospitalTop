package view;

import servidor.ServerMulticast;
import util.Constantes;

public class Main {

	public static void main(String[] args) {

        ServerMulticast serverMulticast = new ServerMulticast(Constantes.MC_MULE_IP.getValor(), Constantes.MC_MULE_PORT.getValor());
        serverMulticast.run();


	}

}
