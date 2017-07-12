package fpt.main;

import fpt.controller.ControllerClient;
import fpt.model.IDgenerator;
import fpt.model.Model;
import fpt.sockets.TCPClient;
import fpt.view.ViewClient;
import fpt.view.ViewServer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by STELLA on 10/05/2017.
 */
public class MainClient extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        IDgenerator.init(model);
        ViewClient viewClient = new ViewClient();
        ControllerClient controllerClient = new ControllerClient();
        TCPClient tcpClient = new TCPClient("client","music");
        controllerClient.link(model,viewClient);
        viewClient.link(controllerClient);

        Scene scene = new Scene(viewClient, 1000, 630);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Playlist");
        primaryStage.setResizable(true);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
