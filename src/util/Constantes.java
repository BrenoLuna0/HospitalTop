package util;

public enum Constantes {

    /*
     * Constantes importantes que devem ser verificadas ao trocar de rede, IP, Computador...
     * */

    BD_ADDRESS("localhost"),
    BD_PORT("27017"),
    BD_NAME("hospitalTop"),
    MC_MULE_IP("230.231.232.233"),
    MC_MULE_PORT("1234"),
    MC_HOSP_IP("230.231.232.234"),
    MC_HOSP_PORT("1234");


    private String infor;

    Constantes(String infor) {
        this.infor = infor;
    }

    public String getValor(){
        return this.infor;
    }
}
