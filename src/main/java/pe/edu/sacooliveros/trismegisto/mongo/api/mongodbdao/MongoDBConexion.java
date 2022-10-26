package pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConexion {

    public MongoClient crearConexion(){
        String uri = "mongodb://54.209.161.184:27017";
        try {
            MongoClient cliente = MongoClients.create(uri);
            System.out.println("Connected to MongoDB!");
            return cliente;
        } catch (MongoException e) {
            System.out.println("Error has occurred: " + e.getMessage());
            return null;
        }
    }
    
}
