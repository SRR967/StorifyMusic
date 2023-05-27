package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ThumbnailDetails;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;



public class YoutubeImage implements Serializable {
    private  final String API_KEY = "AIzaSyD8vXlcYr6QxqWiQ7XYTU22TBXsrko-xf8";
    private String videoLink;
    private String nombreArchivo;
    private static final long serialVersionUID = 1L;


    public YoutubeImage(String videoLink, String nombreArchivo) {
        this.videoLink = videoLink;
        this.nombreArchivo = nombreArchivo;
    }

    public boolean instancia() {
        try {
            YouTube youtubeService = getService();


            // Obtener información del video
            YouTube.Videos.List videoRequest = youtubeService.videos().list(Collections.singletonList("snippet"));
            videoRequest.setKey(API_KEY);
            videoRequest.setId(Collections.singletonList(videoLink));
            VideoListResponse response = videoRequest.execute();
            List<Video> videos = response.getItems();

            if (!videos.isEmpty()) {
                Video video = videos.get(0);
                descargarImagen(video);
                return true;
            }else {
                System.out.println("No se encontró el video");
                return false;
            }
        }catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private  void descargarImagen(Video video) throws IOException {
        // Obtener las diferentes resoluciones de las imágenes disponibles
        ThumbnailDetails thumbnails = video.getSnippet().getThumbnails();

        String highestResolutionUrl = thumbnails.getMaxres() != null ? thumbnails.getMaxres().getUrl() :
                thumbnails.getStandard() != null ? thumbnails.getStandard().getUrl() :
                        thumbnails.getHigh() != null ? thumbnails.getHigh().getUrl() :
                                thumbnails.getMedium() != null ? thumbnails.getMedium().getUrl() :
                                        thumbnails.getDefault().getUrl();

        URL url = new URL(highestResolutionUrl);
        InputStream in = url.openStream();
        // Ruta de destino para guardar la imagen
        String destinationPath = "src/main/resources/imagenes/"+nombreArchivo+".png";
        Path destination = Path.of(destinationPath);

        // Descarga y guarda la imagen
        Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Imagen descargada correctamente.");
    }


    private static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        final JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        HttpRequestInitializer requestInitializer = request -> {
            // No se requiere inicialización adicional
        };
        return new YouTube.Builder(httpTransport, jsonFactory, requestInitializer)
                .setApplicationName("Mi aplicación")
                .build();
    }


    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
}
