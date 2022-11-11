package pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pe.edu.sacooliveros.trismegisto.mongo.api.dao.MatriculaMongoDAO;

public class MatriculaMongoDB implements MatriculaMongoDAO {

    @Override
    public JSONObject matriculaListar() throws Exception {
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.crearConexion().getDatabase("test");
        MongoCollection<Document> coleccion = db.getCollection("ma_informe");
        Iterable<Document> documentos = coleccion.find();
        JSONArray lista;
        lista=new JSONArray();

        for (Document documento : documentos) {
            lista.put(new JSONObject(documento.toJson()));
        }

        return new JSONObject()
                .put("status", true)
                .put("message", "Cantidad de registros: " + lista.length())
                .put("data", lista);
    }

    @Override
    public JSONObject matriculaMigrar(String json) throws Exception{

        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.crearConexion().getDatabase("test");
        MongoCollection<Document> coleccion = db.getCollection("ma_informe");
        Document documento = Document.parse(json);
        coleccion.insertOne(documento);

        return new JSONObject()
                .put("status", true)
                .put("message", new JSONObject(json));
    }

}
