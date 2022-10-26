package pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao;

import org.json.JSONObject;
import pe.edu.sacooliveros.trismegisto.mongo.api.dao.AcademiaDAO;

public class AcademiaMongoDB implements AcademiaDAO {

    @Override
    public JSONObject aulaCrear(JSONObject entrada) throws Exception {
        return new JSONObject()
                .put("status", true)
                .put("message", "Éxito")
                .put("data", entrada);
    }

}
