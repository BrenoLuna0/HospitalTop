package conexaoMongo;

import java.util.ArrayList;

import classesBase.Constantes;
import classesBase.Paciente;
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

@SuppressWarnings({"Duplicates", "LoopStatementThatDoesntLoop"})
public class ConPaciente {

	private MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://"+ Constantes.BD_ADDRESS.getValor()+":"+ Constantes.BD_PORT.getValor()));
	private MongoDatabase db = mongoClient.getDatabase(Constantes.BD_NAME.getValor());
	private MongoCollection<Document> collection = db.getCollection("paciente");

	/**
	 * Insere o Paciente no BD
	 * */
	public void inserir(Paciente p) {

		collection.insertOne(montarDocument(p));
	}

	/**
	 * Remove o Paciente do BD
	 **/
	public void remover(Paciente p) {

		collection.deleteOne(montarDocument(p));
	}

	/**
	 * Atualizar o Paciente no BD
	 **/
	public void atualizar(Paciente antigo, Paciente novo) {

		collection.replaceOne(montarDocument(antigo), montarDocument(novo));
	}

	/**
	 * Busca o Paciente pelo _id (ObjectId Mongo)
	 * */
	public Paciente buscarPorId(String _id) {
		FindIterable<Document> documents = collection.find(eq("_id", _id));

		for (Document d: documents) {
			return montarObjeto(d.toJson());
		}
		return null;
	}

	/**
	 * Busca o Paciente pelo cpf
	 * */
	public Paciente buscarPorCpf(String cpf) {
		FindIterable<Document> documents = collection.find(eq("cpf", cpf));

		for (Document d: documents) {
			return montarObjeto(d.toJson());
		}
		return null;
	}

	/**
	 * Busca de Paciente pelo nome
	 * */
	public ArrayList<Paciente> buscarPorNome(String nome) {

		MongoCursor<Document> documents = collection.find(regex("nome", nome)).iterator();
		return montarArrayList(documents);
	}

	/**
	 * Listar todos os médicos contidos no BD
	 *
	 * @return ArrayList<Paciente> contendo os médicos encontrados no BD (null se não tiver nenhum Paciente)
	 * */
	public ArrayList<Paciente> listar() {

		MongoCursor<Document> cursor = collection.find().iterator();
		return montarArrayList(cursor);
	}

	/**
	 * Recebe um objeto Paciente e retorna o documento correspondente
	 **/
	private Document montarDocument(Paciente m){
		return Document.parse(m.toJson());
	}

	/**
	 * Recebe um Document e retorna o objeto correspondente
	 **/
	private Paciente montarObjeto(String json){
		return new Gson().fromJson(json, Paciente.class);
	}

	/**
	 * Recebe um cursor Document e retorna o ArrayList correspondente
	 **/
	private ArrayList<Paciente> montarArrayList(MongoCursor<Document> cursor){

		ArrayList<Paciente> pacientes = new ArrayList<>();
		try {
			while (cursor.hasNext()) {
				pacientes.add(montarObjeto(cursor.next().toJson()));
			}
		} finally {
			cursor.close();
		}
		return pacientes;
	}
}
