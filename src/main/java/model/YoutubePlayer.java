package model;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;


public class YoutubePlayer extends Application {
    private static final String YOUTUBE_VIDEO_ID = "Q5CL1b-FD9E";

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Habilita JavaScript en el WebEngine
        webEngine.setJavaScriptEnabled(true);

        // Agrega un listener para detectar cuando el documento HTML se ha cargado completamente
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                // Obtén el objeto JavaScript de la página cargada
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", new JavaConnector());
                // Ejecuta el código JavaScript para iniciar la reproducción del video
                webEngine.executeScript("playVideo('" + YOUTUBE_VIDEO_ID + "')");
            }
        });

        // Carga la página HTML que contiene el reproductor de YouTube
        webEngine.loadContent(getYouTubeHTML());

        StackPane root = new StackPane();
        root.getChildren().add(webView);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            // Detener la reproducción del video
            webEngine.executeScript("player.pauseVideo();");
            System.out.println("Se ha cerrado la ventana.");

        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Retorna el código HTML necesario para mostrar el reproductor de YouTube
    private String getYouTubeHTML() {
        return "<html><body>" +
                "<div id='player'></div>" +
                "<script>" +
                "var player;" +
                "function playVideo(videoId) {" +
                "  player = new YT.Player('player', {" +
                "    videoId: videoId," +
                "    events: {" +
                "      'onReady': onPlayerReady" +
                "    }" +
                "  });" +
                "}" +
                "function onPlayerReady(event) {" +
                "  event.target.playVideo();" +
                "}" +
                "function stopVideo() {" +
                "  player.stopVideo();" +
                "  javaConnector.onVideoStopped();" +
                "}" +
                "</script>" +
                "<script src='https://www.youtube.com/iframe_api'></script>" +
                "</body></html>";
    }

    // Objeto de conexión Java-JavaScript
    public class JavaConnector {
        // Método llamado desde JavaScript para comunicarse con Java
        public void onVideoEnded() {
            System.out.println("El video ha terminado");
            // Realiza alguna acción adicional después de que el video haya terminado
        }
    }
}
