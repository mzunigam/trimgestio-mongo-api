package pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConexion {

    public MongoClient crearConexion(){

       //String connectionString =   "mongodb+srv://mongodb:y2tBYLxzCcagFVm@trismegisto.89epuok.mongodb.net/trismegisto?retryWrites=true&w=majority";
      
       String connectionString = "mongodb://54.209.161.184:27017";
      
        try {

            return MongoClients.create(connectionString);

        } catch (MongoException e) {
            System.out.println("Error has occurred: " + e.getMessage());
            return null;
        }
    }
    

}
