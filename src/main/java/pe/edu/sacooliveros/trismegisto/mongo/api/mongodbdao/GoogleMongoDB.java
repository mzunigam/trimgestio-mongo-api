package pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao;

import org.json.JSONObject;

import pe.edu.sacooliveros.trismegisto.mongo.api.api.GoogleConfig;
import pe.edu.sacooliveros.trismegisto.mongo.api.dao.GoogleDAO;

public class GoogleMongoDB implements GoogleDAO {

    @Override
    public JSONObject readSheet() throws Exception {
       
        GoogleConfig config = new GoogleConfig();
         config.toConnect();

        return new JSONObject();
    }

}
