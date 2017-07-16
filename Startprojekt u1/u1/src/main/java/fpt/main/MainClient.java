package fpt.main;

import fpt.controller.ControllerClient;
import fpt.interfaces.MusikPlayer;
import fpt.model.IDgenerator;
import fpt.model.Model;
import fpt.sockets.TCPClient;
import fpt.view.ViewClient;
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
public class MainClient extends Application {
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        IDgenerator.init(model);
        ViewClient viewClient = new ViewClient();
        /*try{
            LocateRegistry.createRegistry(1090);
            System.out.println("registry started");
        }catch (RemoteException re){
            System.out.println("registry not started");
            re.printStackTrace();
        }*/
        try {
            TCPClient tcpClient = new TCPClient("client", "music");
            MusikPlayer remoteServer = (MusikPlayer) Naming.lookup("//localhost/musicplayer");

            Remote remoteClient = new ControllerClient(model,viewClient);
            Naming.rebind("remoteClient",remoteClient);
            System.out.println("remote created");
            System.out.println("connected");
            remoteServer.fillView(remoteServer.songList());
            System.out.println(remoteServer.songList());
            viewClient.fillSongList(null);
            viewClient.fillSongList(remoteServer.songList());

            //viewClient.link(remoteClient);


            //remoteServer.linkModel(model);

        }catch (Exception e){
            e.printStackTrace();
        }

        Scene scene = new Scene(viewClient, 1000, 630);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Playlist");
        primaryStage.setResizable(true);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
