package util;

import java.util.ArrayList;

public enum Infos {

    /*Informações sobre o hospital*/

    HOSPITAL("Unimed", "95965387000127", "Rua Henrique de Oliveira, 191, Caruaru - PE", "8137194548", new String[]{"Pediatria", "Ortopedia", "Geral"});


    private String razaoSocial;
    private String cnpj;
    private String endereco;
    private String contato;
    private ArrayList<String> especialidades;

    Infos(String razaoSocial, String cnpj, String endereco, String contato, String[] especialidades) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.contato = contato;
        this.especialidades = new ArrayList<>();
        for (String s: especialidades) {
            this.especialidades.add(s);
        }
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getContato() {
        return contato;
    }

    public boolean atendeEspecialidade(String especialidade) {
        return especialidades.contains(especialidade);
    }
}
