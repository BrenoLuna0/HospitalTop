package conexaoMongo;

import java.util.ArrayList;

import util.Constantes;
import classesBase.Hospital;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

@SuppressWarnings("Duplicates")
public class ConHospital {

	private MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://"+ Constantes.BD_ADDRESS.getValor()+":"+ Constantes.BD_PORT.getValor()));
	private MongoDatabase db = mongoClient.getDatabase(Constantes.BD_NAME.getValor());
	private MongoCollection<Document> collection = db.getCollection("hospital");

	/**
	 * Insere o Hospital no BD
	 * */
	public void inserir(Hospital h) {

		collection.insertOne(montarDocument(h));
	}

	/**
	 * Remove o Hospital do BD
	 **/
	public void remover(Hospital h) {

		collection.deleteOne(montarDocument(h));
	}

	/**
	 * Atualizar o Hospital no BD
	 **/
	public void atualizar(Hospital antigo, Hospital novo) {

		collection.replaceOne(montarDocument(antigo), montarDocument(novo));
	}

	/**
	 * Busca o Hospital pelo _id (ObjectId Mongo)
	 * */
	public Hospital buscarPorId(String _id) {
		FindIterable<Document> documents = collection.find(eq("_id", _id));

		for (Document d: documents) {
			return montarObjeto(d.toJson());
		}
		return null;
	}

	/**
	 * Busca o Hospital pelo cpf
	 * */
	public Hospital buscarPorCnpj(String cnpj) {
		FindIterable<Document> documents = collection.find(eq("cnpj", cnpj));

		for (Document d: documents) {
			return montarObjeto(d.toJson());
		}
		return null;
	}

	/**
	 * Busca de Hospital pela razão social
	 * */
	public ArrayList<Hospital> buscarPorRazaoSocial(String razao) {

		MongoCursor<Document> documents = collection.find(regex("razaoSocial", razao)).iterator();
		return montarArrayList(documents);
	}

	/**
	 * Listar todos os hospitais contidos no BD
	 *
	 * @return ArrayList<Hospital> contendo os hospitais encontrados no BD (null se não tiver nenhum Hospital)
	 * */
	public ArrayList<Hospital> listar() {

		MongoCursor<Document> cursor = collection.find().iterator();
		return montarArrayList(cursor);
	}

	/**
	 * Recebe um objeto Hospital e retorna o documento correspondente
	 **/
	private Document montarDocument(Hospital m){
		return Document.parse(m.toJson());
	}

	/**
	 * Recebe um Document e retorna o objeto correspondente
	 **/
	private Hospital montarObjeto(String json){
		return new Gson().fromJson(json, Hospital.class);
	}

	/**
	 * Recebe um cursor Document e retorna o ArrayList correspondente
	 **/
	private ArrayList<Hospital> montarArrayList(MongoCursor<Document> cursor){

		ArrayList<Hospital> hospitals = new ArrayList<>();
		try {
			while (cursor.hasNext()) {
				hospitals.add(montarObjeto(cursor.next().toJson()));
			}
		} finally {
			cursor.close();
		}
		return hospitals;
	}
}
