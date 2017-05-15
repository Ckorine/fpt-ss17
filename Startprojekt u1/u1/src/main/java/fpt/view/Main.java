package fpt.view;

import com.sun.corba.se.spi.activation.Server;
import fpt.controller.Controller;
import fpt.model.Model;
import fpt.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by STELLA on 10/05/2017.
 */
public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();
        controller.link(model,view);

        Scene scene = new Scene(view, 600, 500);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Playlist");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // @Override

}
