package fpt.controller;


import fpt.interfaces.ButtonAction;
import fpt.interfaces.Song;
import fpt.model.Model;
import fpt.view.View;

import java.rmi.RemoteException;


/**
 * Created by corin on 09.05.2017.
 */
public class Controller implements ButtonAction {
    private View view;
    private Model model;

    public Controller() {

    }

    public void link (Model model,View view){
        this.model = model;
        this.view = view;

        view.link(this);

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

        //view.fillPlayList(model.getPlaylist());

    }



    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }



}




