package fpt.controller;

import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import fpt.Strategy.BinaryStrategy;
import fpt.Strategy.DatabaseUtils;
import fpt.Strategy.OpenJPA;
import fpt.Strategy.XMLStrategy;
import fpt.interfaces.SerializableStrategy;
import fpt.interfaces.Song;
import fpt.model.Model;
import fpt.model.SongList;
import fpt.view.View;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;


import static javafx.scene.media.MediaPlayer.Status.*;


/**
 * Created by corin on 09.05.2017.
 */
public class Controller {

    private static final String PATH = "C:\\Users\\benja\\Music\\lieder";
    public static final String[] strategies = {"Binary Strategy", "OpenJPA", "XML Strategy", "JDBCConnector"};

    private SerializableStrategy strategy;
    private View view;
    private Model model;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Media currentSong;
    private int seekForwarTime = 5000; // milliseconde


    public Controller() {

    }

    public void link(Model model, View view) {
        this.model = model;
        this.view = view;

        model.addSongsFromDir(PATH);
        view.fillSongList(model.getAllSongs());
        view.fillPlayList(model.getPlaylist());


        view.getAddToPlayButton().setOnAction(event -> {
            Song s = view.getSongList().getSelectionModel().getSelectedItem();
            if (s == null) {
                return;
            }
            try {
                model.getPlaylist().addSong(s);
                view.fillPlayList(null);
                view.fillPlayList(model.getPlaylist());

            } catch (RemoteException e) {
                e.printStackTrace();
            }
            System.out.println("mediaPlayer is " + mediaPlayer);

        });

        view.getAddall().setOnMouseClicked(event -> {

            try {
                model.getPlaylist().deleteAllSongs();
                SongList sl = model.getAllSongs();
                for (Song s : sl) {
                    model.getPlaylist().addSong(s);
                }
                view.fillPlayList(null);
                view.fillPlayList(model.getPlaylist());

            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (model.getPlaylist().isEmpty()) {
                return;
            } else if (mediaPlayer != null) {
                mediaPlayer.getMedia();
            }
        });

        view.getRemoveFromPLay().setOnMousePressed(event -> {
            Song s = view.getPlayList().getSelectionModel().getSelectedItem();
            if (s == null) {
                return;
            }
            try {
                Media media = new Media(s.getPath());

                if (mediaPlayer == null) {
                    model.getPlaylist().deleteSong(s);
                    view.fillPlayList(null);
                    view.fillPlayList(model.getPlaylist());


                    return;
                }

                if (mediaPlayer != null && mediaPlayer.getMedia().getSource().equals(media.getSource())) {

                    model.getPlaylist().deleteSong(s);
                    stop();
                    view.fillPlayList(null);
                    view.fillPlayList(model.getPlaylist());
                } else {
                    model.getPlaylist().deleteSong(s);
                    view.fillPlayList(null);
                    view.fillPlayList(model.getPlaylist());

                }
                /*if(strategy == dbutils){
                    dbutils.deleteSongWithID(s.getId());
                }*/

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        });

        view.getPlay().setOnAction(e ->
        {
            play();
        });

        view.getStop().setOnAction(e ->
        {
            stop();
        });
        view.getPause().setOnAction(e ->
        {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
        });
        view.getNext().setOnAction(event -> {
            playNext();
        });
        view.getLoad().setOnAction(event -> {
            try {
                load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        view.getSave().setOnAction(event -> {
            try {
                save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void playNext() {
        if (model.getPlaylist().isEmpty()) {
            return;
        }

        int index = view.getPlayList().getSelectionModel().getSelectedIndex();
            System.out.println(index);
            if (index == -1 || index == model.getPlaylist().size() - 1) {
            view.getPlayList().getSelectionModel().clearAndSelect(0);
        } else {
            view.getPlayList().getSelectionModel().clearAndSelect(index + 1);
        }

        play();


    }

    private void play() {
        if (model.getPlaylist().isEmpty()) {
            return;
        }
        Song s = view.getPlayList().getSelectionModel().getSelectedItem();
        if (s == null) {
            s = model.getPlaylist().get(0);
            view.getPlayList().getSelectionModel().clearAndSelect(0);
            s = view.getPlayList().getSelectionModel().getSelectedItem();


        }
        Media media = new Media(s.getPath());
        if (mediaPlayer != null) {
            if (mediaPlayer.getMedia().getSource().equals(media.getSource())) {
                MediaPlayer.Status st = mediaPlayer.getStatus();
                if (st == PAUSED || st == READY || st == STOPPED || st == STALLED) {
                    mediaPlayer.play();
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

    public void stop() {
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
        view.fillSongList(null);
        System.out.println("balaka");

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


                    }
            view.fillSongList(model.getAllSongs());

              System.out.println("bOlakO");

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
                    view.fillPlayList(null);
                    view.fillPlayList(model.getPlaylist());
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
    //rien
    //{}
    }

}







