package pe.edu.sacooliveros.trismegisto.mongo.api.service;

import org.json.JSONObject;

import pe.edu.sacooliveros.trismegisto.mongo.api.dao.FactoryDAO;
import pe.edu.sacooliveros.trismegisto.mongo.api.dao.MatriculaMongoDAO;

public class MatriculaService {

    MatriculaMongoDAO dao = FactoryDAO.getFactoryDAO(FactoryDAO.MONGODB).getMatriculaDAO();

    public JSONObject matriculaListar() throws Exception {
        return dao.matriculaListar();
    }

    public JSONObject matriculaMigrar(String json) throws Exception{
        return dao.matriculaMigrar(json);
    }
    
}
