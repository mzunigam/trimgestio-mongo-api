package pe.edu.sacooliveros.trismegisto.mongo.api.google;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.LinkedHashMap;

import java.util.Map;

import org.json.JSONObject;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.client.util.StringUtils;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;


public class GmailService {

    private static final String APPLICATION_NAME = "Trismegisto";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String user = "mzuniga.ti@sacooliveros.edu.pe";
    static Gmail service = null;
    private static File filePath = new File(System.getProperty("user.home") + "/credentials.json");
    // public static void main(String[] args) {

    //     try {
    //         getGmailService();

    //         getMailBody("prueba2");
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }


    // }

    public static String getMailBody(String searchString) throws IOException {

		Gmail.Users.Messages.List request = service.users().messages().list(user).setQ(searchString);

		ListMessagesResponse messagesResponse = request.execute();
		request.setPageToken(messagesResponse.getNextPageToken());

		String messageId = messagesResponse.getMessages().get(0).getId();

		Message message = service.users().messages().get(user, messageId).execute();

		String emailBody = StringUtils.newStringUtf8(Base64.decodeBase64(message.getPayload().getParts().get(0).getBody().getData()));
        
        String emailSubject = message.getPayload().getHeaders().get(19).getValue();

        System.out.println(emailSubject+" : "+emailBody);

        return emailSubject+" "+emailBody;

	}

	public static Gmail getGmailService() throws IOException, GeneralSecurityException {

        InputStream in = new FileInputStream(filePath);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		Credential authorize = new GoogleCredential.Builder().setTransport(GoogleNetHttpTransport.newTrustedTransport())
				.setJsonFactory(JSON_FACTORY)
				.setClientSecrets(clientSecrets.getDetails().getClientId().toString(),
						clientSecrets.getDetails().getClientSecret().toString())
				.build()
                .setAccessToken(getAccessToken())
                .setRefreshToken("1//0fRpVW_NCsC9vCgYIARAAGA8SNwF-L9IrrynrPfXv9AuRKUY3biR6rvzQGYKDuglh07ugNQZ4I8JP4j93YwxqAz8Ir0rOPhXa524");

                final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
                service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, authorize)
                        .setApplicationName(GmailService.APPLICATION_NAME).build();
        
                return service;
                        
    }

	private static String getAccessToken() {

		try {
			Map<String, Object> params = new LinkedHashMap<>();
			params.put("grant_type", "refresh_token");
			params.put("client_id", "475621380483-rijfi66cgrjsc4a4fqd239lstulpvlkt.apps.googleusercontent.com"); 
			params.put("client_secret", "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"); 
			params.put("refresh_token","1//0fRpVW_NCsC9vCgYIARAAGA8SNwF-L9IrrynrPfXv9AuRKUY3biR6rvzQGYKDuglh07ugNQZ4I8JP4j93YwxqAz8Ir0rOPhXa524"); 

			StringBuilder postData = new StringBuilder();
			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0) {
					postData.append('&');
				}
				postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
				postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}
			byte[] postDataBytes = postData.toString().getBytes("UTF-8");

			URL url = new URL("https://accounts.google.com/o/oauth2/token");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.getOutputStream().write(postDataBytes);

			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				buffer.append(line);
			}

			JSONObject json = new JSONObject(buffer.toString());
			String accessToken = json.getString("access_token");
			return accessToken;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public class getGmailService {
	}

}
