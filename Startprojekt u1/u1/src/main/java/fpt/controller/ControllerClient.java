package fpt.controller;

import fpt.Strategy.BinaryStrategy;
import fpt.Strategy.DatabaseUtils;
import fpt.Strategy.OpenJPA;
import fpt.Strategy.XMLStrategy;
import fpt.interfaces.MusikPlayer;
import fpt.interfaces.RemoteClient;
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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import static javafx.scene.media.MediaPlayer.Status.*;


/**
 * Created by corin on 09.05.2017.
 */
public class ControllerClient extends UnicastRemoteObject implements RemoteClient {
    private MusikPlayer remote;
    private ViewClient view;
    private Model model;

    public ControllerClient(Model model,ViewClient viewClient) throws RemoteException{
        this.view = view;
        this.model = model;


    }

    public void link(Model model, ViewClient viewClient) throws RemoteException  {
        this.model = model;
        this.view = view;
        /*MusikPlayer remoteServer = null;
        try {
            remoteServer = (MusikPlayer) Naming.lookup("//localhost/musicplayer");
            //viewClient.fillSongList(null);
            //view.fillSongList(remoteServer.songList());

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
    }
    public void linkModel(Model model) throws  RemoteException{
        this.model = model;
    }

    @Override
    public SongList songList() {
        return null;
    }

    @Override
    public void playNext() {

    }

    @Override
    public void play() {
        //View.ActAsIfSomethingIsPlaying()
    }

    @Override
    public void stopButton() {

    }

    @Override
    public void setStrategy(int a) {

    }

    @Override
    public void load() throws IOException {

    }

    @Override
    public void save() throws IOException {

    }
}







