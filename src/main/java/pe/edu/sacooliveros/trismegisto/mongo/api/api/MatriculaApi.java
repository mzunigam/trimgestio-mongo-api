package pe.edu.sacooliveros.trismegisto.mongo.api.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import pe.edu.sacooliveros.trismegisto.mongo.api.service.MatriculaService;

@Path("matricula")
@Consumes("application/json")
@Produces("application/json")
public class MatriculaApi {

    @POST
    @Path("listar")
    public Response matriculaListar() {
        JSONObject salida = new JSONObject();
        try {
            salida = new MatriculaService().matriculaListar();
            return Response.status(200).entity(salida.toString()).build();
            
        } catch (Exception ex) {
            salida
                    .put("status", false)
                    .put("message", ex.getMessage())
                    .put("data", JSONObject.NULL);

            throw new WebApplicationException(Response.status(500).entity(salida.toString()).build());
        }
    }

    @POST
    @Path("migrar")
    public Response matriculaMigrar(String json) {
        JSONObject salida = new JSONObject();
        try {
            salida = new MatriculaService().matriculaMigrar(json);
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
