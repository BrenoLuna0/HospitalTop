package conexaoMongo;

import java.util.ArrayList;

import util.Constantes;
import classesBase.Medico;
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

@SuppressWarnings({"Duplicates", "LoopStatementThatDoesntLoop"})
public class ConMedico{

    private MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://"+Constantes.BD_ADDRESS.getValor()+":"+Constantes.BD_PORT.getValor()));
	private MongoDatabase db = mongoClient.getDatabase(Constantes.BD_NAME.getValor());
	private MongoCollection<Document> collection = db.getCollection("medico");

    /**
     * Insere o Medico no BD
     * */
	public void inserir(Medico m) {

		collection.insertOne(montarDocument(m));
	}

    /**
     * Remove o Medico do BD
     **/
	public void remover(Medico m) {

        collection.deleteOne(montarDocument(m));
	}

    /**
     * Atualizar o Medico no BD
     **/
	public void atualizar(Medico antigo, Medico novo) {

        collection.replaceOne(montarDocument(antigo), montarDocument(novo));
	}

    /**
     * Busca o Medico pelo _id (ObjectId Mongo)
     * */
	public Medico buscarPorId(String _id) {
        FindIterable<Document> documents = collection.find(eq("_id", _id));

        for (Document d: documents) {
            return montarObjeto(d.toJson());
        }
		return null;
	}

    /**
     * Busca de Medico pelo CRM
     * */
	public Medico buscarPorCrm(String crm) {
        FindIterable<Document> documents = collection.find(eq("crm", crm));

        for (Document d: documents) {
            return montarObjeto(d.toJson());
        }
        return null;
	}

    /**
     * Busca de Medico pelo nome
     * */
	public ArrayList<Medico> buscarPorNome(String nome) {

        MongoCursor<Document> documents = collection.find(regex("nome", nome)).iterator();
        return montarArrayList(documents);
	}

	/**
     * Listar todos os médicos contidos no BD
     *
     * @return ArrayList<Medico> contendo os médicos encontrados no BD (null se não tiver nenhum Medico)
     * */
	public ArrayList<Medico> listar() {

        MongoCursor<Document> cursor = collection.find().iterator();
        return montarArrayList(cursor);
	}

	/**
     * Recebe um objeto Medico e retorna o documento correspondente
     **/
	private Document montarDocument(Medico m){
        return Document.parse(m.toJson());
    }

    /**
     * Recebe um Document e retorna o objeto correspondente
     **/
    private Medico montarObjeto(String json){
	    return new Gson().fromJson(json, Medico.class);
    }

    /**
     * Recebe um cursor Document e retorna o ArrayList correspondente
     **/
    private ArrayList<Medico> montarArrayList(MongoCursor<Document> cursor){

        ArrayList<Medico> medicos = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                medicos.add(montarObjeto(cursor.next().toJson()));
            }
        } finally {
            cursor.close();
        }
        return medicos;
    }
}
