package classesBase;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

public class Paciente {
	
	static final int ID_GENERATOR = 0;

	private int ID;
	private String nome;
	private String cpf;
	private String contato;
	private ArrayList<String> remediosComAlergia;

	public Paciente(String nome, String cpf, String contato, String[] remedios) {
		this.ID = ID_GENERATOR +1;
		this.nome = nome;
		this.cpf = cpf;
		this.contato = contato;
		
		this.remediosComAlergia = new ArrayList<String>();
		for(int i = 0; i < remedios.length; i++) {
			this.remediosComAlergia.add(remedios[i]);
		}
	}

	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public ArrayList<String> getRemediosComAlergia() {
		return remediosComAlergia;
	}

	public void setRemediosComAlergia(String[] remedios) {
		for(int i = 0; i < remedios.length; i++) {
			this.remediosComAlergia.add(remedios[i]);
		}
	}

	public int getID() {
		return ID;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getContato() {
		return contato;
	}

	public void solicitarConsulta(String[] sintomas, Date data) {
		
	}
}