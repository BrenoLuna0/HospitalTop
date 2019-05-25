package conexaoMongo;

import java.util.ArrayList;

import classesBase.Medico;
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;


@SuppressWarnings("Duplicates")
public class ConMedico{

	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	MongoDatabase db = mongoClient.getDatabase("hospitalTop");
	MongoCollection<Document> collection = db.getCollection("medico");

    /**
     * Insere o Medico no BD
     * */
	public void inserir(Medico m) {

        Document doc = Document.parse(m.toJson());
		collection.insertOne(doc);
	}

    /**
     * Remove o Medico do BD
     * */
	public void remover(Medico m) {
        Document doc = Document.parse(m.toJson());
        collection.deleteOne(doc);
	}

    /**
     * Remove o Medico do BD
     * */
	public void atualizar(Medico antigo, Medico novo) {

        collection.replaceOne(Document.parse(antigo.toJson()), Document.parse(novo.toJson()));
	}

    /**
     * Busca de Medico pelo _id (ObjectId Mongo)
     * */
	public Medico buscarPorId(int _id) {
        FindIterable<Document> documents = collection.find(eq("_id", _id));

        for (Document d: documents) {
            Medico m = new Gson().fromJson(d.toJson(), Medico.class);
            return m;
        }

		return null;
	}

    /**
     * Busca de Medico pelo CRM
     * */
	public Medico buscarPorCrm(String crm) {
        FindIterable<Document> documents = collection.find(eq("crm", crm));

        for (Document d: documents) {
            Medico m = new Gson().fromJson(d.toJson(), Medico.class);
            return m;
        }
        return null;
	}

    /**
     * Busca de Medico pelo nome
     * */
	public ArrayList<Medico> buscarPorNome(String nome) {
        ArrayList<Medico> medicos = new ArrayList<>();

        FindIterable<Document> documents = collection.find(eq("nome", nome));

        for (Document d: documents) {
            Medico m = new Gson().fromJson(d.toJson(), Medico.class);
            medicos.add(m);
        }

        return medicos;
	}

	/**
     * Listar todos os médicos contidos no BD
     *
     * @return ArrayList<Medico> contendo os médicos encontrados no BD (null se não tiver nenhum Medico)
     * */
	public ArrayList<Medico> listar() {
	    ArrayList<Medico> medicos = new ArrayList<>();

        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Gson gson = new Gson();
                Medico m = gson.fromJson(cursor.next().toJson(), Medico.class);
                medicos.add(m);
            }
        } finally {
            cursor.close();
        }

        return medicos;
	}

}
