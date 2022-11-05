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

            if (entrada.isNull("aula_academia_nombre")) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST+" | aula_academia_nombre is required ");
            } else {
                AcademiaService service = new AcademiaService();
                salida = service.aulaCrear(new JSONObject()
                        .put("aula_academia_nombre", entrada.getString("aula_academia_nombre")));
                return Response.status(200).entity(salida.toString()).build();
            }
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

    @PUT
    @Path("aula")
    public Response aulaActualizar(String json) {
        JSONObject salida = new JSONObject();

        try {
            JSONObject entrada = new JSONObject(json);

            if (entrada.isNull("aula_academia_id")||entrada.isNull("aula_academia_nombre")) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST+ " | aula_academia_id and aula_academia_nombre are required ");
            } else {
                AcademiaService service = new AcademiaService();
                salida = service.aulaActualizar(new JSONObject()
                        .put("aula_academia_id", entrada.getInt("aula_academia_id"))
                        .put("aula_academia_nombre", entrada.getString("aula_academia_nombre")));
                return Response.status(200).entity(salida.toString()).build();
            }
        } catch (Exception ex) {
            salida
                    .put("status", false)
                    .put("message", ex.getMessage())
                    .put("data", JSONObject.NULL);

            throw new WebApplicationException(Response.status(500).entity(salida.toString()).build());
        }
    }

    @DELETE
    @Path("aula")
    public Response aulaEliminar(String json) {
        JSONObject salida = new JSONObject();

        try {
            JSONObject entrada = new JSONObject(json);

            if (entrada.isNull("aula_academia_id")) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST+ " | aula_academia_id is required ");
            } else {
                AcademiaService service = new AcademiaService();
                salida = service.aulaEliminar(new JSONObject()
                        .put("aula_academia_id", entrada.getInt("aula_academia_id")));
                return Response.status(200).entity(salida.toString()).build();
            }

        } catch (Exception ex) {
            salida
                    .put("status", false)
                    .put("message", ex.getMessage())
                    .put("data", JSONObject.NULL);

            throw new WebApplicationException(Response.status(500).entity(salida.toString()).build());
        }
    }

}
