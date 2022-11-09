package pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao;

import com.mongodb.ConnectionString;

import com.mongodb.MongoClientSettings;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
public class MongoDBConexion {

    public MongoClient crearConexion(){

        ConnectionString connectionString = new ConnectionString("mongodb+srv://mongodb:y2tBYLxzCcagFVm@trismegisto.89epuok.mongodb.net/admin?replicaSet=atlas-brae88-shard-0&readPreference=primary&srvServiceName=mongodb&connectTimeoutMS=10000&authSource=admin&authMechanism=SCRAM-SHA-1");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(settings);
    }

}
