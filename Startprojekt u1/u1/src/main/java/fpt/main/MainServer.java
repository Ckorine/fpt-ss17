package fpt.main;

import fpt.controller.ControllerClient;
import fpt.controller.ControllerServer;
import fpt.model.IDgenerator;
import fpt.model.Model;
import fpt.sockets.TCPClient;
import fpt.sockets.TCPServer;
import fpt.view.ViewServer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by STELLA on 10/05/2017.
 */
public class MainServer extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        IDgenerator.init(model);
        ViewServer view = new ViewServer();
        ControllerServer controllerServer = new ControllerServer();
        TCPServer tcpServer = new TCPServer("music","musicplayer");
        controllerServer.link(model,view);
        view.link(controllerServer);

        Scene scene = new Scene(view, 1000, 630);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Playlist");
        primaryStage.setResizable(true);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // @Override

}
