package fpt.controller;

import fpt.Strategy.BinaryStrategy;
import fpt.Strategy.DatabaseUtils;
import fpt.Strategy.OpenJPA;
import fpt.Strategy.XMLStrategy;
import fpt.interfaces.MusikPlayer;
import fpt.interfaces.SerializableStrategy;
import fpt.interfaces.Song;
import fpt.model.Model;
import fpt.model.SongList;
import fpt.sockets.UDPClient;
import fpt.view.ViewClient;
import fpt.view.ViewServer;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.TimeUnit;


import static java.lang.Thread.sleep;
import static javafx.scene.media.MediaPlayer.Status.*;


/**
 * Created by corin on 09.05.2017.
 */
public class ControllerServer extends UnicastRemoteObject implements MusikPlayer {

    private static final String PATH = "C:\\Users\\corin\\Desktop\\Sommersmester 2017\\FPT\\Aufgabe\\Lieder";
    public static final String[] strategies = {"Binary Strategy", "OpenJPA", "XML Strategy", "JDBCConnector"};

    private SerializableStrategy strategy;
    private ViewServer viewServer;
    private Model model;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Media currentSong;
    private int seekForwarTime = 5000;// milliseconde
    private Duration duration;
    private Slider timeSlider;
    private static String temps = "";


    public ControllerServer() throws RemoteException {
        super();

    }
    public String returnZeit() {

            // UI updaten
        Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // entsprechende UI Komponente updaten
                    Duration currentTime = mediaPlayer.getCurrentTime();
                    duration = mediaPlayer.getMedia().getDuration();
                    temps = formatTime(currentTime,duration);
                    viewServer.fillTimeBox(UDPClient.getZeit());

                }
            });

            // Thread schlafen
            try {
                // fuer 1 Sekunde
                sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException ex) {

            }
        return temps;

    }

    public void link(Model model, ViewServer viewServer) {
        this.model = model;
        this.viewServer = viewServer;

        model.addSongsFromDir(PATH);
        viewServer.fillSongList(model.getAllSongs());
        viewServer.fillPlayList(model.getPlaylist());



        viewServer.getAddToPlayButton().setOnAction(event -> {
            Song s = viewServer.getSongList().getSelectionModel().getSelectedItem();
            if (s == null) {
                return;
            }
            try {
                model.getPlaylist().addSong(s);
                viewServer.fillPlayList(null);
                viewServer.fillPlayList(model.getPlaylist());

            } catch (RemoteException e) {
                e.printStackTrace();
            }
            System.out.println("mediaPlayer is " + mediaPlayer);

        });

        viewServer.getAddall().setOnMouseClicked(event -> {

            try {
                model.getPlaylist().deleteAllSongs();
                SongList sl = model.getAllSongs();
                for (Song s : sl) {
                    model.getPlaylist().addSong(s);
                }
                viewServer.fillPlayList(null);
                viewServer.fillPlayList(model.getPlaylist());

            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (model.getPlaylist().isEmpty()) {
                return;
            } else if (mediaPlayer != null) {
                mediaPlayer.getMedia();
            }
        });

        viewServer.getRemoveFromPLay().setOnMousePressed(event -> {
            Song s = viewServer.getPlayList().getSelectionModel().getSelectedItem();
            if (s == null) {
                return;
            }
            try {
                Media media = new Media(s.getPath());
                if (mediaPlayer == null) {
                    model.getPlaylist().deleteSong(s);
                    viewServer.fillPlayList(null);
                    viewServer.fillPlayList(model.getPlaylist());
                    return;
                }

                if (mediaPlayer != null && mediaPlayer.getMedia().getSource().equals(media.getSource())) {

                    model.getPlaylist().deleteSong(s);
                    stopButton();
                    viewServer.fillPlayList(null);
                    viewServer.fillPlayList(model.getPlaylist());
                } else {
                    model.getPlaylist().deleteSong(s);
                    viewServer.fillPlayList(null);
                    viewServer.fillPlayList(model.getPlaylist());

                }

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        });

        viewServer.getPlay().setOnAction(e ->
        {
            play();
            returnZeit();


        });


        viewServer.getStop().setOnAction(e ->
        {
            stopButton();
        });
        viewServer.getPause().setOnAction(e ->
        {
            if (mediaPlayer != null) {
                mediaPlayer.pause();

            }
        });
        viewServer.getNext().setOnAction(event -> {
            playNext();
        });
        viewServer.getLoad().setOnAction(event -> {
            try {
                load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        viewServer.getSave().setOnAction(event -> {
            try {
                save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void playNext() {
        if (model.getPlaylist().isEmpty()) {
            return;
        }

        int index = viewServer.getPlayList().getSelectionModel().getSelectedIndex();
        System.out.println(index);
        if (index == -1 || index == model.getPlaylist().size() - 1) {
            viewServer.getPlayList().getSelectionModel().clearAndSelect(0);
        } else {
            viewServer.getPlayList().getSelectionModel().clearAndSelect(index + 1);
        }

        play();


    }

    public void play() {
        if (model.getPlaylist().isEmpty()) {
            return;
        }
        Song s = viewServer.getPlayList().getSelectionModel().getSelectedItem();
        if (s == null) {
            s = model.getPlaylist().get(0);
            viewServer.getPlayList().getSelectionModel().clearAndSelect(0);
            s = viewServer.getPlayList().getSelectionModel().getSelectedItem();


        }
        Media media = new Media(s.getPath());
        if (mediaPlayer != null) {
            if (mediaPlayer.getMedia().getSource().equals(media.getSource())) {
                MediaPlayer.Status st = mediaPlayer.getStatus();
                if (st == PAUSED || st == READY || st == STOPPED || st == STALLED) {
                    mediaPlayer.play();
                    System.out.println("mediaPlayer is " + mediaPlayer);

                }
                return;
            } else {
                mediaPlayer.stop();
            }
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.getMedia();
        mediaPlayer.setOnEndOfMedia(() -> {
            playNext();

        });
    }
    public static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.compareTo(Duration.ZERO)>0) {
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 -
                    durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }




    public void stopButton() {
        if (mediaPlayer != null) {
            if (!mediaPlayer.isMute())
                mediaPlayer.stop();
        }
    }

    public void setStrategy(int strategycase) {
        switch (strategycase) {
            case 0:
                strategy = new BinaryStrategy();
                break;
            case 1:
                strategy = new OpenJPA();
                break;
            case 2:
                strategy = new XMLStrategy();
                break;
            case 3:
                strategy = new DatabaseUtils();
                break;
        }
        System.out.println("Strategy:" + strategycase);
        System.out.println(strategy + " 4");
    }

    public void load() throws IOException {


        model.getAllSongs().deleteAllSongs();
        viewServer.fillSongList(null);

        if (model.getPlaylist() != null) {
            model.getPlaylist().deleteAllSongs();
        }

        try {
            strategy.openReadableSongs();
            Song readSongL = null;
            readSongL = strategy.readSong();

            while (readSongL != null) {
                model.getAllSongs().addSong(readSongL);
                readSongL = strategy.readSong();
                viewServer.fillSongList(model.getAllSongs());
            }

            System.out.println("Library loaded!!!");
        } catch (IOException|ClassNotFoundException e) {
            System.out.println("Library loaded");
            e.printStackTrace();
            //no more song to be read
        } finally {
            strategy.closeReadable();
        }

        try {
            strategy.openReadablePlaylist();
            Song readSong = strategy.readSong();
            Song result = null;
            long id =0;
            while (readSong!=null) {
                id = readSong.getId();
                if(id == model.getAllSongs().findSongByID(id).getId()) {
                    result = model.getAllSongs().findSongByID(id);
                    model.getPlaylist().addSong(result);
                    readSong = strategy.readSong();
                    viewServer.fillPlayList(null);
                    viewServer.fillPlayList(model.getPlaylist());
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Playlist loaded");
            e.printStackTrace();
            //different song´s id
        } finally {
            strategy.closeReadable();
        }
    }


    public void save() throws IOException {
        System.out.println(strategy + " 1");
        //System.out.println("Song  inserted");
        try {
            //System.out.println(strategy + " 2");
            strategy.openWriteableSongs();

            //System.out.println("Song  inserted");
            for (Song s : model.getAllSongs()) {
                //System.out.println(strategy);
                strategy.writeSong(s);
                //System.out.println("Song " + s.getTitle() + " inserted");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            strategy.closeWriteable();
        }
        try {
            strategy.openWriteablePlaylist();

            for (Song s : model.getPlaylist()) {
                strategy.writeSong(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            strategy.closeWriteable();
        }

    }

    public static String getTemps() {
        return temps;
    }

}







