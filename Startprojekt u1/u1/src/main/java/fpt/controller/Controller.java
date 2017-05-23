package fpt.controller;

import fpt.interfaces.Song;
import fpt.model.Model;
import fpt.model.SongList;
import fpt.view.View;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;



import static javafx.scene.media.MediaPlayer.Status.*;


/**
 * Created by corin on 09.05.2017.
 */
public class Controller {
    private static final String PATH = "C:\\Users\\corin\\Documents\\GitHub\\fpt-ss17\\Startprojekt u1\\u1\\src\\res\\files";
    private View view;
    private Model model;
    private Media media;
    private MediaPlayer mediaPlayer ;
    private Media currentSong;
    private int seekForwarTime = 5000; // milliseconde


    public Controller() {

    }

    public void link(Model model, View view) {
        this.model = model;
        this.view = view;

        model.setSongsFromDir(PATH);

        view.fillSongList(model.getAllSongs());
        view.fillPlayList(model.getPlaylist());

        view.getAddToPlayButton().setOnAction(event -> {
            Song s = view.getSongList().getSelectionModel().getSelectedItem();
            try {
                model.getPlaylist().addSong(s);
                view.fillPlayList(null);
                view.fillPlayList(model.getPlaylist());

            } catch (RemoteException e) {
                e.printStackTrace();
            }
            currentSong=mediaPlayer.getMedia();
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
        });

        view.getRemoveFromPLay().setOnMousePressed(event -> {
            Song s = view.getPlayList().getSelectionModel().getSelectedItem();
            try {
                model.getPlaylist().deleteSong(s);
                view.fillPlayList(null);
                view.fillPlayList(model.getPlaylist());

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
            if(mediaPlayer!= null){
                if(!mediaPlayer.isMute())
                    mediaPlayer.stop();
            }

        });
        view.getPause().setOnAction(e ->
        {
            if(mediaPlayer!= null){
                    mediaPlayer.pause();
            }
        });
        view.getNext().setOnAction(event -> {
            playNext();
        });




       /* mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.stop();
        });*/
    }

    private void playNext() {
        if(model.getPlaylist().isEmpty()) {
            return;
        }

        int index = view.getPlayList().getSelectionModel().getSelectedIndex();
        if(index == -1 || index == model.getPlaylist().size() - 1){
            view.getPlayList().getSelectionModel().clearAndSelect(0);
        } else {
            view.getPlayList().getSelectionModel().clearAndSelect(index + 1);
        }

        play();

        /*int listsize = view.size8();
        int i = 0;
        for (i = 0; i < listsize; i++) {
            if (model.getPlaylist().get(i) == s) {
                break;
            }

        }
        if (i < model.getPlaylist().size() - 1) {
            s = model.getPlaylist().get(i + 1);
            view.getPlayList().getSelectionModel().clearAndSelect(i+1);
            if (mediaPlayer !=null) {
                mediaPlayer.stop();
                String s1 = s.getPath();
                media = new Media(s1);
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setOnEndOfMedia(() -> {
                    playNext();
                });
                mediaPlayer.play();
            }
        } else {
            System.out.println("no songs selected");
        }*/
    }

    private void play() {
        if(model.getPlaylist().isEmpty()) {
            return;
        }
        Song s = view.getPlayList().getSelectionModel().getSelectedItem();
        if(s == null){
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
        currentSong = mediaPlayer.getMedia();
        mediaPlayer.setOnEndOfMedia(() -> {
            playNext();

        });

    }
}




