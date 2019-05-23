package conexaoMongo;

import java.util.ArrayList;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import classesBase.Hospital;

public class ConHospital {
	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

	public void inserir(Object o) {
		
		

	}

	public void remover(Object o) {

	}

	public boolean atualizar(Hospital antigo, Hospital novo) {
		return false;
	}

	public Hospital buscarPorId(int ID) {
		return null;
	}

	public Hospital buscarPorCnpj(String cnpj) {
		return null;
	}

	public Hospital buscarPorRazaoSocial(String razaoSocial) {
		return null;
	}

	public ArrayList<Hospital> listar() {
		return null;
	}

}
