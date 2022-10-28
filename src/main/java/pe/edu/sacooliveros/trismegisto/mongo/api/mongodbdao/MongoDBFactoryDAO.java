package pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao;

import pe.edu.sacooliveros.trismegisto.mongo.api.dao.AcademiaDAO;
import pe.edu.sacooliveros.trismegisto.mongo.api.dao.FactoryDAO;
import pe.edu.sacooliveros.trismegisto.mongo.api.dao.GoogleDAO;

public class MongoDBFactoryDAO extends FactoryDAO {

    @Override
    public AcademiaDAO getAcademiaDAO() {
       return new AcademiaMongoDB();
    }

    @Override
    public GoogleDAO getGoogleDAO() {
        return new GoogleMongoDB();
    }

}
