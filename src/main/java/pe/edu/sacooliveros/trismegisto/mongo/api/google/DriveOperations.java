package pe.edu.sacooliveros.trismegisto.mongo.api.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.UUID;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.client.http.FileContent;

public class DriveOperations {

    public static File getSpecificFile( String fileId) throws IOException, GeneralSecurityException {

        File file = GoogleToken.getDriveService().files().get(fileId).execute();
        return file;
            
    }

    public static List<File> getFiles() throws IOException, GeneralSecurityException {
        Drive service = GoogleToken.getDriveService();
        FileList result = service.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute();
        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            return null;
        } else {
            return files;
        }
    }

    public static String uploadFile() throws IOException, GeneralSecurityException {
        Drive service = GoogleToken.getDriveService();
        File fileMetadata = new File();
        UUID uuid = UUID.randomUUID();
        fileMetadata.setName(uuid.toString());
        java.io.File filePath = new java.io.File(System.getProperty("user.home")+"/files/logotipo.png");
        FileContent mediaContent = new FileContent("image/png", filePath);
        File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        return file.getId();
    }


}
