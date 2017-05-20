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
        view.getRemoveFromPLay().setOnMouseClicked(event -> {
            Song s = view.getPlayList().getSelectionModel().getSelectedItem();
            try {
                model.getPlaylist().deleteSong(s);
                view.fillPlayList(null);
                view.fillPlayList(model.getPlaylist());


            } catch (RemoteException e) {
                e.printStackTrace();
            }

            @Override
            public void stop () {

            }

        public void display () {

        }
    }
   /* public ArrayList<Song> getList() throws RemoteException {
          return null;  */

   /* public boolean addSong(Song s) throws RemoteException {
=======
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        //view.fillPlayList(model.getPlaylist());
>>>>>>> 08c23b45335067a8d4693ac0273a52325dd1a1ff

    }



    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }



}




