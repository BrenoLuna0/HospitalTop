package classesBase;

import com.google.gson.Gson;

public class Hospital {
	static final int ID_GENERATOR = 0;
	
	private int ID;
	private String razaoSocial;
	private String cnpj;
	private String endereco;
	private String contato;
	private boolean status;
	
	public Hospital(String razaoSocial, String cnpj, String endereco, String contato) {
		ID = ID_GENERATOR +1;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.contato = contato;
		this.status = true;
		
	}
	
	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getID() {
		return ID;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getContato() {
		return contato;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
	

}
