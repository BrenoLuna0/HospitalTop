package classesBase;

public enum Constantes {

    BD_ADDRESS("localhost"),
    BD_PORT("27017"),
    BD_NAME("hospitalTop");


    private String infor;

    Constantes(String infor) {
        this.infor = infor;
    }

    public String getValor(){
        return this.infor;
    }
}
