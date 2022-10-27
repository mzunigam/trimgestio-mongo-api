package pe.edu.sacooliveros.trismegisto.mongo.api.service;

import org.json.JSONException;
import org.json.JSONObject;
import pe.edu.sacooliveros.trismegisto.mongo.api.dao.AcademiaDAO;
import pe.edu.sacooliveros.trismegisto.mongo.api.dao.FactoryDAO;

public class AcademiaService {

    AcademiaDAO dao = FactoryDAO.getFactoryDAO(FactoryDAO.MONGODB).getAcademiaDAO();

    public JSONObject aulaCrear(JSONObject entrada) throws JSONException, Exception {
        return dao.aulaCrear(entrada);
    }

    public JSONObject aulaListar() throws JSONException, Exception {
        return dao.aulaListar();
    }

    public JSONObject aulaActualizar (JSONObject entrada) throws JSONException, Exception {
        return dao.aulaActualizar(entrada);
    }

    public JSONObject aulaEliminar (JSONObject entrada) throws JSONException, Exception {
        return dao.aulaEliminar(entrada);
    }

}
