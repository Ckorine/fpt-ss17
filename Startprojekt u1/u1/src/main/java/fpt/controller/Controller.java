package fpt.controller;


import fpt.interfaces.ButtonAction;
import fpt.model.Model;
import fpt.view.View;


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
    }

    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }

    public void display() {

    }
   /* public ArrayList<Song> getList() throws RemoteException {
          return null;  */

   /* public boolean addSong(Song s) throws RemoteException {

    }


    public boolean deleteSong(Song s) throws RemoteException {
        return list.remove(s);
    }

    public void setList(ArrayList<Song> s) throws RemoteException {

    }


    public ArrayList<Song> getList() throws RemoteException {

    }


    public void deleteAllSongs() throws RemoteException {

    }


    public int sizeOfList() throws RemoteException {

    }


    public Song findSongByPath(String name) throws RemoteException {

    }


    public Iterator<Song> iterator() {
        return null;
    }


    public Song get(int index) {

    }


    public int size() {

    }


    protected void doAdd(int index, Song element) {

    }


    protected Song doSet(int index, Song element) {

    }


    protected Song doRemove(int index) {

    }*/
}




