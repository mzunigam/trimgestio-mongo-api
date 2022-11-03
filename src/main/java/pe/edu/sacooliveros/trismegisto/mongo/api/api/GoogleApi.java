package pe.edu.sacooliveros.trismegisto.mongo.api.api;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import pe.edu.sacooliveros.trismegisto.mongo.api.google.GmailOperations;
import pe.edu.sacooliveros.trismegisto.mongo.api.google.GoogleToken;

@Path("google")
@Produces("application/json")
@Consumes("application/json")
public class GoogleApi {

    @GET
    @Path("email")
    @Produces("text/plain")
    public String getEmail() {

            return "";
    }

    @POST
    @Path("email")
    public Response sendEmail(String json) {
        try {

            JSONObject entrada = new JSONObject(json);
            String respuesta =  GmailOperations.postEmail(entrada.getString("to"), entrada.getString("from"), entrada.getString("subject"), entrada.getString("bodyText"));
            entrada.put("respuesta", respuesta);

            return Response.status(200).entity(entrada.toString()).build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("email/html")
    public Response sendEmailHtml(String json) {
        try {

            JSONObject entrada = new JSONObject(json);
            String respuesta =  GmailOperations.sendEmailWithHTML(entrada.getString("to"), entrada.getString("subject"), entrada.getString("htmlText"));
            entrada.put("respuesta", respuesta);

            return Response.status(200).entity(entrada.toString()).build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }


    @GET
    @Path ("token/token")
    public Response getToken(String json) {
        try {
            JSONObject entrada = new JSONObject(json);
            String token = GoogleToken.getAccessToken(entrada.getString("refresh_token"));
            entrada.put("token",token);
            
            return Response.status(200).entity(entrada.toString()).build();
            
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }


}
