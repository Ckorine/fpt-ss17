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
            if(s==null){
                return;
            }
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
            if(model.getPlaylist().isEmpty()) {
                return;
            } else if(mediaPlayer !=null){
                currentSong = mediaPlayer.getMedia();
            }
        });

        view.getRemoveFromPLay().setOnMousePressed(event -> {
            Song s = view.getPlayList().getSelectionModel().getSelectedItem();
            if(s==null){
                return;
            }
            try {
                Media media = new Media(s.getPath());
                if(mediaPlayer==null){
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
            if(mediaPlayer!= null){
                mediaPlayer.pause();
            }
        });
        view.getNext().setOnAction(event -> {
            playNext();
        });
    }

    private void playNext() {
        if(model.getPlaylist().isEmpty()) {
            return;
        }

        int index = view.getPlayList().getSelectionModel().getSelectedIndex();
        System.out.println(index);
        if(index == -1 || index == model.getPlaylist().size() - 1){
            view.getPlayList().getSelectionModel().clearAndSelect(0);
        } else {
            view.getPlayList().getSelectionModel().clearAndSelect(index + 1);
        }

        play();


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
    public void stop(){
        if(mediaPlayer!= null){
            if(!mediaPlayer.isMute())
                mediaPlayer.stop();
        }


    }
}




