package pe.edu.sacooliveros.trismegisto.mongo.api.google;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;



public class GmailOperations {

    public static void sendMessage(Gmail service, String userId, MimeMessage email)
            throws MessagingException, IOException {
        Message message = createMessageWithEmail(email);
        message = service.users().messages().send(userId, message).execute();

        System.out.println("Message id: " + message.getId());
        System.out.println(message.toPrettyString());
    }

    public static Message createMessageWithEmail(MimeMessage email) throws MessagingException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        email.writeTo(baos);
        String encodedEmail = Base64.encodeBase64URLSafeString(baos.toByteArray());
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

	public static MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException, IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(from)); 
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to)); 
		email.setSubject(subject); 

        email.setText(bodyText);
        
		return email;
	}


    public static String postEmail(String to, String from, String subject, String bodyText)  {
        
        try {
            
            Gmail service = GmailService.getGmailService();

           MimeMessage mimeMessage = createEmail(to, from, subject, bodyText);
           Message message =  createMessageWithEmail(mimeMessage);
        
           message = service.users().messages().send("me", message).execute();

            return "Exito";

        } catch (Exception e) {

            return "Error";

        }


    }


    
	public static MimeMessage createHTMLEmailBodyWithAttachment(String to, String subject, String html) throws AddressException, MessagingException {
        
		
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);
        
        email.setFrom(new InternetAddress("me"));
        
        String[] split = to.split(",");
        for(int i=0;i<split.length;i++) { 
        	email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(split[i]));
        }
    
        email.setSubject(subject);

        Multipart multiPart = new MimeMultipart("mixed");
        
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(html, "text/html; charset=utf-8");  
        multiPart.addBodyPart(htmlPart,0);
       
        
        // //Attachments ...
        // MimeBodyPart mimeBodyPart = new MimeBodyPart();
		// // DataSource source = new FileDataSource(new File(htmlReportPath));
		// // mimeBodyPart.setDataHandler(new DataHandler(source));
		// mimeBodyPart.setFileName("https://res.cloudinary.com/pgsama/image/upload/v1667410882/zypsaybxltq9aj8vvar8.png");
		// multiPart.addBodyPart(mimeBodyPart,1);
		
        
        email.setContent(multiPart);
        return email;
    }

    public static String sendEmailWithHTML(String to,String subject, String htmlText) throws IOException, AddressException, MessagingException, GeneralSecurityException {
		
        // Document doc = Jsoup.parse(new File("src/main/webapp/formato.html"), "utf-8"); 
        
        // Elements Tags = doc.getElementsByTag("html");
        
        // String body = Tags.first().html();
     
		// String htmlText = "<html>"+ body +"</html>";
	
		
		Gmail service = GmailService.getGmailService();
		MimeMessage Mimemessage = createHTMLEmailBodyWithAttachment(to,subject,htmlText);
	
		Message message = createMessageWithEmail(Mimemessage);
		
		message = service.users().messages().send("me", message).execute();
		
        return "Exito";
	}



}
