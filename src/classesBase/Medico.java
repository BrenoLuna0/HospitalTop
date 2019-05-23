package classesBase;

import java.util.ArrayList;

public class Medico {
	static final int ID_GENERATOR = 0;

	private int ID;
	private String nome;
	private String crm;
	private ArrayList<String> especializacao;
	private boolean status;
	
	public Medico(String nome, String crm, String[] especializacao) {
		ID = ID_GENERATOR +1;
		
		this.nome = nome;
		this.crm = crm;
		this.especializacao = new ArrayList<String>();
		for(int i = 0; i < especializacao.length; i++) {
			this.especializacao.add(especializacao[i]);
		}
		this.status = true;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getID() {
		return ID;
	}

	public String getNome() {
		return nome;
	}

	public String getCrm() {
		return crm;
	}

	public ArrayList<String> getEspecializacao() {
		return especializacao;
	}
	
	
	

}
