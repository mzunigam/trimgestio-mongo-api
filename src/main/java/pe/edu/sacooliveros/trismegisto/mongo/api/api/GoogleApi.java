package pe.edu.sacooliveros.trismegisto.mongo.api.api;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.json.JSONObject;


import pe.edu.sacooliveros.trismegisto.mongo.api.service.GoogleService;


@Path("google")
@Produces("application/json")
public class GoogleApi {

    @GET
    @Path("data")
    public String readSheet() throws Exception {
        GoogleService service = new GoogleService();
        service.readSheet();
       return "Hola";
    }
    
}
