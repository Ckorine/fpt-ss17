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
import fpt.sockets.TCPClient;
import fpt.sockets.TCPServer;
import fpt.sockets.UDPClient;
import fpt.sockets.UDPServer;
import fpt.view.ViewClient;
import fpt.view.ViewServer;
import javafx.application.Platform;
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
import java.util.Timer;
import java.util.TimerTask;


import static javafx.scene.media.MediaPlayer.Status.*;


/**
 * Created by corin on 09.05.2017.
 */
public class ControllerClient extends UnicastRemoteObject implements RemoteClient {
    private static final long serialVersionUID = 10L;
    private MusikPlayer remote;
    private ViewClient viewClient;
    private Model model;
    private UDPClient udpClient;
    private SongList songList = new SongList();
    Timer timer = new Timer();


    public ControllerClient(Model model,ViewClient viewClient) throws RemoteException{
        this.viewClient = viewClient;
        this.model = model;
        try {
            udpClient = new UDPClient();
        }catch (Exception e){
            e.printStackTrace();
        }


        viewClient.getAddToPlayButton().setOnAction(event -> {
            Song s = viewClient.getSongList().getSelectionModel().getSelectedItem();
            if (s == null) {
                return;
            }
            try {
                synchronized (TCPClient.returnDienstameS()) {
                    try {
                        MusikPlayer remoteServer = (MusikPlayer) Naming.lookup("//localhost/" + TCPClient.returnDienstameS());
                        synchronized (remoteServer){
                            try {
                                remoteServer.addToPlay(s.getId());
                            }catch (Exception e){

                            }

                        }
                    } catch (NotBoundException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }


        });
        viewClient.getCommit().setOnAction(event -> {
            Song s = viewClient.getSongList().getSelectionModel().getSelectedItem();
            if (s == null) {
                return;
            }
            synchronized (TCPClient.getDienstname()) {
                try {
                    MusikPlayer remoteServer = null;
                    try {
                        remoteServer = (MusikPlayer) Naming.lookup("//localhost/" + TCPClient.returnDienstameS());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    synchronized (remoteServer){
                        remoteServer.commit(s.getId(),viewClient.getTitelS().getText(),viewClient.getInterpret().getText(),viewClient.getAlbum().getText());
                    }
                } catch (NotBoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        });
        viewClient.getPlay().setOnAction(event->{
            Song s = viewClient.getPlayList().getSelectionModel().getSelectedItem();
            try {
                MusikPlayer remoteServer =(MusikPlayer) Naming.lookup("//localhost/"+ TCPClient.returnDienstameS() );
                remoteServer.play(s.getId());
            } catch (NotBoundException e1) {
                e1.printStackTrace();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
        viewClient.getStop().setOnAction(event -> {
            try {
                MusikPlayer remoteServer =(MusikPlayer) Naming.lookup("//localhost/"+ TCPClient.returnDienstameS());
                synchronized (remoteServer) {
                    remoteServer.stopButton();
                }
            } catch (NotBoundException e1) {
                e1.printStackTrace();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
        viewClient.getPause().setOnAction(event -> {
            try {
                MusikPlayer remoteServer =(MusikPlayer) Naming.lookup("//localhost/"+ TCPClient.returnDienstameS());
                synchronized (remoteServer) {
                    remoteServer.pause();
                }
            } catch (NotBoundException e1) {
                e1.printStackTrace();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }

        });
        viewClient.getAddall().setOnAction(event -> {
            try {
                MusikPlayer remoteServer =(MusikPlayer) Naming.lookup("//localhost/"+ TCPClient.returnDienstameS());
                synchronized (remoteServer) {
                    remoteServer.addAll();
                }
            } catch (NotBoundException e1) {
                e1.printStackTrace();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
        viewClient.getNext().setOnAction(event -> {
            Song s = viewClient.getPlayList().getSelectionModel().getSelectedItem();
            try {
                MusikPlayer remoteServer =(MusikPlayer) Naming.lookup("//localhost/"+ TCPClient.returnDienstameS());
                synchronized (remoteServer) {
                    remoteServer.play(s.getId());
                }
            } catch (NotBoundException e1) {
                e1.printStackTrace();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }

        });


    }
    @Override
    public SongList songList() throws RemoteException {
        return null;
    }

    @Override
    public void playNext() throws RemoteException {
        try {

            udpClient.connetToServer();

            System.out.println("udpClient started");

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            viewClient.fillTimeBox(udpClient.getZeit());
                        }
                    },0,1000);

                }

            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void play() throws RemoteException {

        try {

            udpClient.connetToServer();

            System.out.println("udpClient started");

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            viewClient.fillTimeBox(udpClient.getZeit());
                        }
                    },0,1000);

                }

            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void pause() throws RemoteException{
        udpClient.connetToServer();
        viewClient.fillTimeBox(udpClient.getZeit());
    }

    public void link(Model model, ViewClient viewClient) throws RemoteException  {
        try {
            this.model = model;
            this.viewClient = viewClient;
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void stopButton() throws RemoteException {
        try {
            udpClient.connetToServer();
            viewClient.fillTimeBox(udpClient.getZeit());
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void setStrategy(int a) throws RemoteException {

    }

    @Override
    public void load() throws IOException, RemoteException {

    }

    @Override
    public void save() throws IOException, RemoteException {

    }

    @Override
    public void fillSongs(long id, String titel, String interpret, String album) throws RemoteException {
        fpt.model.Song song = new fpt.model.Song();
        if (titel!=null) {
            song.setId(id);
            song.setTitle(titel);
            song.setInterpret(interpret);
            song.setAlbum(album);
            model.getAllSongs().addSong(song);
            viewClient.fillSongList(null);
            viewClient.fillSongList(model.getAllSongs());
        }


    }

    @Override
    public void addToPlay(long id )throws RemoteException {
        Song song = model.getAllSongs().findSongByID(id);
        System.out.print(song);
        if (song !=null) {
            try {
                model.getPlaylist().addSong(song);
                viewClient.fillPlayList(null);
                viewClient.fillPlayList(model.getPlaylist());
            }catch (Exception e){

            }
        }
    }

    public void addAll(){
        try {
            model.getPlaylist().deleteAllSongs();
            SongList sl = model.getAllSongs();
            for (Song s : sl) {
                model.getPlaylist().addSong(s);
            }
            viewClient.fillPlayList(null);
            viewClient.fillPlayList(model.getPlaylist());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (model.getPlaylist().isEmpty()) {
            return;
        }

    }
    public static String getTemps() throws RemoteException {
        return null;
    }
    public void commitSong(long id,String titel,String interpret,String album) throws RemoteException{
        Song selectedSong = model.getAllSongs().findSongByID(id);
        selectedSong.setAlbum(album);
        selectedSong.setInterpret(interpret);
        selectedSong.setTitle(titel);

    }


}







