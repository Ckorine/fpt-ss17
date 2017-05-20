package fpt.controller;

import fpt.interfaces.Song;
import fpt.model.Model;
import fpt.model.SongList;
import fpt.view.View;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.rmi.RemoteException;


/**
 * Created by corin on 09.05.2017.
 */
public class Controller {
    private View view;
    private Model model;
    private Media media ;
    MediaPlayer mediaPlayer ;

    public Controller() {

    }

    public void link(Model model, View view) {
        this.model = model;
        this.view = view;

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
        });

        view.getAddall().setOnMouseClicked(event -> {
            try {
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

        view.getPLay().setOnAction(e ->
        {

                if (mediaPlayer != null  ) {
                    if (!mediaPlayer.isMute()) {
                        mediaPlayer.stop();
                    }
                }

            Song s = view.getPlayList().getSelectionModel().getSelectedItem();
            String s1 = s.getPath();
            media = new Media(new File(s1).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();



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
                if(!mediaPlayer.isMute())
                    mediaPlayer.pause();
            }

        });

    }
}




