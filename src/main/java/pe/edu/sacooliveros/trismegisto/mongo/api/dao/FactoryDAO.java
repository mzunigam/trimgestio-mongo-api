package pe.edu.sacooliveros.trismegisto.mongo.api.dao;

import pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao.MongoDBFactoryDAO;

public abstract class FactoryDAO extends DAO {

    public static final int MONGODB = 1;
    public static final int POSTGRESQL = 2;

    public static FactoryDAO getFactoryDAO(int whichFactory) {
        switch (whichFactory) {
            case MONGODB:
                return new MongoDBFactoryDAO();
            /*case POSTGRESQL:
                return new PostgreSqlFactoryDAO();*/
            default:
                return null;
        }
    }

    public abstract AcademiaDAO getAcademiaDAO();

}
