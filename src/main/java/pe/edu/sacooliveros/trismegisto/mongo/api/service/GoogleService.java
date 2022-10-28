package pe.edu.sacooliveros.trismegisto.mongo.api.service;

import org.json.JSONObject;

import pe.edu.sacooliveros.trismegisto.mongo.api.dao.FactoryDAO;
import pe.edu.sacooliveros.trismegisto.mongo.api.dao.GoogleDAO;

public class GoogleService {
    
    GoogleDAO dao = FactoryDAO.getFactoryDAO(FactoryDAO.MONGODB).getGoogleDAO();

    public JSONObject readSheet() throws Exception {
        return dao.readSheet();
    }

}
