package pe.edu.sacooliveros.trismegisto.mongo.api.dao;

import org.json.JSONObject;

public interface AcademiaDAO {

    public JSONObject aulaCrear(JSONObject entrada) throws Exception;

    public JSONObject aulaListar() throws Exception;

    public JSONObject aulaActualizar (JSONObject entrada) throws Exception;

    public JSONObject aulaEliminar (JSONObject entrada) throws Exception;

}
