package fpt.controller;

import fpt.interfaces.Song;
import fpt.model.Model;
import fpt.model.SongList;
import fpt.view.View;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.rmi.RemoteException;

import static javafx.scene.media.MediaPlayer.Status.*;


/**
 * Created by corin on 09.05.2017.
 */
public class Controller {
    private static final String PATH = "C:\\Users\\corin\\Documents\\GitHub\\fpt-ss17\\Startprojekt u1\\u1\\src\\res\\files";
    private View view;
    private Model model;
    private MediaPlayer mediaPlayer ;
    private Song currentSong;

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

        view.getPlay().setOnAction(e ->
        {
            Song s = view.getPlayList().getSelectionModel().getSelectedItem();
            if(s == null){
               s = model.getPlaylist().get(0);
            }
            Media media = new Media(s.getPath());
            if (mediaPlayer != null) {
                if (mediaPlayer.getMedia().getSource().equals(media.getSource())) {
                    MediaPlayer.Status st = mediaPlayer.getStatus();
                    if (st == PAUSED || st == READY || st == STOPPED || st == STALLED) {
                        mediaPlayer.play();
                        return;
                    }
                } else {
                    mediaPlayer.stop();
                }
           }
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
                    mediaPlayer.pause();
            }
        });

       /* mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.stop();
        });*/
    }
}




