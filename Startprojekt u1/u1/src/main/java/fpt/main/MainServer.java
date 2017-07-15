package fpt.main;

import fpt.controller.ControllerClient;
import fpt.controller.ControllerServer;
import fpt.interfaces.MusikPlayer;
import fpt.model.IDgenerator;
import fpt.model.Model;
import fpt.sockets.TCPClient;
import fpt.sockets.TCPServer;
import fpt.view.ViewServer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

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
        try {
            Remote remote = new ControllerServer(model,view);//Model is here 0, have to link
            Naming.rebind("musicplayer", remote);

            System.out.println("musicplayer started");
        }catch(RemoteException ex) {
            System.out.println("error");
            ex.printStackTrace();
        }

        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("registry started");
        }catch (RemoteException re){
            System.out.println("registry not started");
            re.printStackTrace();
        }

            TCPServer tcpServer = new TCPServer("music");
            tcpServer.start();
            System.out.println("server started");


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
