package pe.edu.sacooliveros.trismegisto.mongo.api.api;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.google.api.services.drive.model.File;

import pe.edu.sacooliveros.trismegisto.mongo.api.google.DriveOperations;
import pe.edu.sacooliveros.trismegisto.mongo.api.google.GmailOperations;
import pe.edu.sacooliveros.trismegisto.mongo.api.google.GoogleToken;

@Path("google")
@Produces("application/json")
@Consumes("application/json")
public class GoogleApi {

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
    @Path ("token/drive")
    public Response getDriveToken() {
        try {
            String token = GoogleToken.getAccessToken("1//0fapdbu9L9BT1CgYIARAAGA8SNwF-L9IrPrzWwbGVdZgWMNoGoex1wTGAlkACuEonlob8AMo98xreNVuKLKrh4I9OVN3FNT7H87w");
            JSONObject entrada = new JSONObject();
            entrada.put("token",token);
            
            JSONObject salida = new JSONObject()
                .put("status", true)
                .put("message", "Éxito")
                .put("data", entrada);
            
            return Response.status(200).entity(salida.toString()).build();
            
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }
    
    @GET
    @Path("token/gmail")
    public Response getGmailToken(){
        try {
            String token = GoogleToken.getAccessToken("1//0fRpVW_NCsC9vCgYIARAAGA8SNwF-L9IrrynrPfXv9AuRKUY3biR6rvzQGYKDuglh07ugNQZ4I8JP4j93YwxqAz8Ir0rOPhXa524");
            JSONObject entrada = new JSONObject();
            entrada.put("token",token);
            
            JSONObject salida = new JSONObject()
                .put("status", true)
                .put("message", "Éxito")
                .put("data", entrada);
            
            return Response.status(200).entity(salida.toString()).build();
            
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("drive/file")
    public Response getDriveFile(String json) {

        try {
            
            JSONObject object = new JSONObject(json);

            File file = DriveOperations.getSpecificFile(object.getString("id"));

            if(file != null){
                JSONObject entrada = new JSONObject();
                entrada.put("mimetype",file.getMimeType());
                entrada.put("name",file.getName());
                entrada.put("id",file.getId());
                
                JSONObject salida = new JSONObject()
                    .put("status", true)
                    .put("message", "Éxito")
                    .put("data", entrada);
                
                return Response.status(200).entity(salida.toString()).build();
            }else{
                return Response.status(404).entity("File with that id does not exist in drive").build();
            }

        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }

    }


}
