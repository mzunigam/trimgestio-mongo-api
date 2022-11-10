package pe.edu.sacooliveros.trismegisto.mongo.api.dao;

import org.json.JSONObject;
import org.mortbay.util.ajax.JSON;

public interface MatriculaMongoDAO {

    public JSONObject matriculaListar() throws Exception;

    public JSONObject matriculaMigrar(String json) throws Exception;

}
