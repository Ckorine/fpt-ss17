package fpt.main;

import fpt.controller.ControllerClient;
import fpt.model.IDgenerator;
import fpt.model.Model;
import fpt.view.View;
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
        View  view = new View();
        ControllerClient controller = new ControllerClient();
        controller.link(model,view);
        view.link(controller);

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
