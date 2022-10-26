package pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao;

import javax.swing.text.Document;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pe.edu.sacooliveros.trismegisto.mongo.api.dao.AcademiaDAO;

public class AcademiaMongoDB implements AcademiaDAO {

    @Override
    public JSONObject aulaCrear(JSONObject entrada) throws Exception {
        
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.crearConexion().getDatabase("prueba");
        MongoCollection<org.bson.Document> coleccion = db.getCollection("co_aula_academia");
        org.bson.Document documento = org.bson.Document.parse(entrada.toString());
        coleccion.insertOne(documento);

        return new JSONObject()
                .put("status", true)
                .put("message", "Éxito")
                .put("data", entrada);
    }

    @Override
    public JSONObject aulaListar() throws Exception {
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.crearConexion().getDatabase("prueba");
        MongoCollection<org.bson.Document> coleccion = db.getCollection("co_aula_academia");
        Iterable<org.bson.Document> documentos = coleccion.find();
        JSONArray lista = new JSONArray();
        
        for (org.bson.Document documento : documentos) {
            lista.put(new JSONObject(documento.toJson()));
        }

        return new JSONObject()
                .put("status", true)
                .put("message", "Éxito")
                .put("data", lista);

    }

    

}
