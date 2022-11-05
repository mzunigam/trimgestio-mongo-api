package pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao;

import org.json.JSONArray;
import org.json.JSONObject;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

import pe.edu.sacooliveros.trismegisto.mongo.api.dao.AcademiaDAO;

public class AcademiaMongoDB implements AcademiaDAO {

    @Override
    public JSONObject aulaCrear(JSONObject entrada) throws Exception {
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.crearConexion().getDatabase("trismegisto");
        MongoCollection<Document> coleccion = db.getCollection("co_aula_academia");
        entrada.put("aula_academia_id", coleccion.countDocuments() + 1);
        Document documento = Document.parse(entrada.toString());
        coleccion.insertOne(documento);

        return new JSONObject()
                .put("status", true)
                .put("message", "Creado correctamente")
                .put("data", entrada);
    }

    @Override
    public JSONObject aulaListar() throws Exception {
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.crearConexion().getDatabase("trismegisto");
        MongoCollection<Document> coleccion = db.getCollection("co_aula_academia");
        Iterable<Document> documentos = coleccion.find();
        JSONArray lista = new JSONArray();

        for (Document documento : documentos) {
            lista.put(new JSONObject(documento.toJson()));
        }

        return new JSONObject()
                .put("status", true)
                .put("message", "Actualizado correctamente")
                .put("data", lista);
    }

    @Override
    public JSONObject aulaActualizar(JSONObject entrada) throws Exception {
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.crearConexion().getDatabase("trismegisto");
        MongoCollection<Document> coleccion = db.getCollection("co_aula_academia");
        Document documento = Document.parse(entrada.toString());

        if (coleccion.find(new Document("aula_academia_id", entrada.getInt("aula_academia_id"))).first() == null) {
            return aulaCrear(new JSONObject().put("aula_academia_nombre", entrada.getString("aula_academia_nombre")));
        }
        else {
            return new JSONObject()
                    .put("status", true)
                    .put("message", "Actualizado correctamente")
                    .put("data", entrada);
        }
    }

    @Override
    public JSONObject aulaEliminar(JSONObject entrada) throws Exception {

        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.crearConexion().getDatabase("trismegisto");
        MongoCollection<Document> coleccion = db.getCollection("co_aula_academia");

        if (coleccion.findOneAndDelete(new Document("aula_academia_id", entrada.getInt("aula_academia_id"))) == null) {
            return new JSONObject()
                    .put("status", false)
                    .put("message", "No Document Found with ID: " + entrada.getInt("aula_academia_id"))
                    .put("data", entrada);
        }

        return new JSONObject()
                .put("status", true)
                .put("message", "Elimado correctamente")
                .put("data", entrada);
    }


}
