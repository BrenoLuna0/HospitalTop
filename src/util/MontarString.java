package util;

import classesBase.Paciente;

public class MontarString {
	
	public static Paciente montarPaciente(String pacote) {
		String nome, cpf, contato, remedios;

		String[] infoPacote = pacote.split(",");
		
		nome = infoPacote[0];
		cpf = infoPacote[1];
		contato = infoPacote[2];
		remedios = infoPacote[3];
		
		remedios = remedios.substring(1, remedios.length()-2);
		
		String[] remediosSeparados = remedios.split(";");
		
		return new Paciente(nome, cpf, contato, remediosSeparados);
		
	}

}
