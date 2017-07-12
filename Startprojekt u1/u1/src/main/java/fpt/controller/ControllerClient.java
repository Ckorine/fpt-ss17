package fpt.controller;

import fpt.Strategy.BinaryStrategy;
import fpt.Strategy.DatabaseUtils;
import fpt.Strategy.OpenJPA;
import fpt.Strategy.XMLStrategy;
import fpt.interfaces.SerializableStrategy;
import fpt.interfaces.Song;
import fpt.model.Model;
import fpt.model.SongList;
import fpt.sockets.UDPClient;
import fpt.sockets.UDPServer;
import fpt.view.ViewClient;
import fpt.view.ViewServer;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.IOException;
import java.rmi.RemoteException;



import static javafx.scene.media.MediaPlayer.Status.*;


/**
 * Created by corin on 09.05.2017.
 */
public class ControllerClient {

    public void link(Model model, ViewClient viewClient) {
    }

}







