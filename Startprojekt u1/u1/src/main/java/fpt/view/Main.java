package fpt.view;

import com.thoughtworks.xstream.core.ReferenceByIdMarshaller;
import fpt.Strategy.DatabaseUtils;
import fpt.controller.Controller;
import fpt.model.IDgenerator;
import fpt.model.Model;
import fpt.model.Song;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        IDgenerator.init(model);
        /*DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.insertSong(song);*/
        View view = new View();
        Controller controller = new Controller();
        controller.link(model,view);

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
