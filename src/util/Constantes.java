package util;

public enum Constantes {

    /*
    * Constantes importantes que devem ser verificadas ao trocar de rede, IP, Computador...
    * */

    UC_IP("10.1.3.204"),
    UC_PORT("1235"),
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
