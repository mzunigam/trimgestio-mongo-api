package pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConexion {

    public MongoClient crearConexion(){

        String connectionString =   "mongodb+srv://mongodb:<password>@trismegisto.89epuok.mongodb.net/test";   
      
       // String connectionString = "mongodb://54.209.161.184:27017";
      
        try {

            MongoClient cliente = MongoClients.create(connectionString);
            System.out.println(cliente.getClusterDescription().getClusterSettings().getHosts());

            return cliente;
        } catch (MongoException e) {
            System.out.println("Error has occurred: " + e.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
        MongoDBConexion mongoDBConexion = new MongoDBConexion();
        mongoDBConexion.crearConexion();
    }
}
