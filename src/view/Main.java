package view;

import servidor.ServerMulticast;
import util.Constantes;

/**Classe do servidor principal que se conecta ao MULE e aos Hospitais*/
public class Main {

	public static void main(String[] args) {

        ServerMulticast serverMulticastMule = new ServerMulticast(Constantes.MC_MULE_IP.getValor(), Constantes.MC_MULE_PORT.getValor());
        serverMulticastMule.run();

	}

}
