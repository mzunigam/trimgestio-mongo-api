package pe.edu.sacooliveros.trismegisto.mongo.api.api;

import javax.ws.rs.*;
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
    @Path("aula/{aula_academia_id}")
    public Response aulaEliminar(@PathParam("aula_academia_id") Integer aula_academia_id) {
        JSONObject salida = new JSONObject();

        try {

            if (aula_academia_id == 0) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST+ " | aula_academia_id is required " + aula_academia_id);
            } else {
                AcademiaService service = new AcademiaService();
                salida = service.aulaEliminar(new JSONObject()
                        .put("aula_academia_id", aula_academia_id));
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
