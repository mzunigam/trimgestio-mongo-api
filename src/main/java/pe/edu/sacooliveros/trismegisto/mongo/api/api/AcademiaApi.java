package pe.edu.sacooliveros.trismegisto.mongo.api.api;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pe.edu.sacooliveros.trismegisto.mongo.api.mongodbdao.MongoDBConexion;
import pe.edu.sacooliveros.trismegisto.mongo.api.service.AcademiaService;

@Path("academia")

@Produces("application/json")
@Consumes("application/json")
public class AcademiaApi {

    @POST
    @Path("aula")
    public Response aulaCrear(String json) {
        JSONObject salida = new JSONObject();

        try {
            JSONObject entrada = new JSONObject(json);

            salida = new AcademiaService().aulaCrear(entrada);

            return Response.status(200).entity(salida.toString()).build();
        } catch (Exception ex) {
            salida
                    .put("status", false)
                    .put("message", ex.getMessage())
                    .put("data", JSONObject.NULL);

            throw new WebApplicationException(Response.status(500).entity(salida.toString()).build());
        }
    }

    @GET
    @Path("aula")
    public Response aulaListar() {
        JSONObject salida = new JSONObject();

        try {
            salida = new AcademiaService().aulaListar();

            return Response.status(200).entity(salida.toString()).build();
        } catch (Exception ex) {
            salida
                    .put("status", false)
                    .put("message", ex.getMessage())
                    .put("data", JSONObject.NULL);

            throw new WebApplicationException(Response.status(500).entity(salida.toString()).build());
        }
    }

}
