package pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao;

import org.json.JSONArray;
import org.json.JSONObject;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pe.edu.sacooliveros.trismegisto.mongo.api.dao.AcademiaDAO;

public class AcademiaMongoDB implements AcademiaDAO {

    @Override
    public JSONObject aulaCrear(JSONObject entrada) throws Exception {
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.crearConexion().getDatabase("prueba");
        MongoCollection<Document> coleccion = db.getCollection("co_aula_academia");

        entrada.put("_id", coleccion.countDocuments() + 1);

        Document documento = Document.parse(entrada.toString());
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
        
        MongoCollection<Document> coleccion = db.getCollection("co_aula_academia");
        Iterable<Document> documentos = coleccion.find();
        JSONArray lista = new JSONArray();
        
        for (Document documento : documentos) {
            lista.put(new JSONObject(documento.toJson()));
        }

        return new JSONObject()
                .put("status", true)
                .put("message", "Éxito")
                .put("data", lista);
    }

    @Override
    public JSONObject aulaActualizar(JSONObject entrada) throws Exception {
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.crearConexion().getDatabase("prueba");
        MongoCollection<Document> coleccion = db.getCollection("co_aula_academia");

        Document documento = Document.parse(entrada.toString());
        coleccion.replaceOne(new Document("_id", entrada.getInt("_id")), documento);

        return new JSONObject()
                .put("status", true)
                .put("message", "Éxito")
                .put("data", entrada);
    }

    @Override
    public JSONObject aulaEliminar(JSONObject entrada) throws Exception {

        System.out.println(entrada.toString());

        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.crearConexion().getDatabase("prueba");
        MongoCollection<Document> coleccion = db.getCollection("co_aula_academia");

        coleccion.deleteOne(new Document("_id", entrada.getInt("_id")));
        System.out.println(new Document("_id", entrada.getInt("_id")).toJson());

        return new JSONObject()
                .put("status", true)
                .put("message", "Éxito")
                .put("data", entrada);
    }

    

}
