package classesBase;

public class Hospital {
	static final int ID_GENERATOR = 0;
	
	private int ID;
	private String razaoSocial;
	private String cnpj;
	private String endereco;
	private long contato;
	private boolean status;
	
	public Hospital(String razaoSocial, String cnpj, String endereco, long contato) {
		ID = ID_GENERATOR +1;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.contato = contato;
		this.status = true;
		
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

	public long getContato() {
		return contato;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
	

}
