package fpt.controller;

import fpt.interfaces.Song;
import fpt.model.Model;
import fpt.view.View;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by corin on 09.05.2017.
 */
public class Controller {
    private View view;
    private Model model;

    public Controller() {

    }

    public void link (Model model,View view){
        this.model = model;
        this.view = view;

        view.link(this);
    }
    /*public boolean addSong(Song s) throws RemoteException {

    }

    @Override
    public boolean deleteSong(Song s) throws RemoteException {
        return list.remove(s);
    }

    @Override
    public void setList(ArrayList<Song> s) throws RemoteException {

    }

    @Override
    public ArrayList<Song> getList() throws RemoteException {

    }

    @Override
    public void deleteAllSongs() throws RemoteException {

    }

    @Override
    public int sizeOfList() throws RemoteException {

    }

    @Override
    public Song findSongByPath(String name) throws RemoteException {

    }

    @Override
    public Iterator<Song> iterator() {
        return null;
    }

    @Override
    public Song get(int index) {

    }

    @Override
    public int size() {

    }

    @Override
    protected void doAdd(int index, Song element) {

    }

    @Override
    protected Song doSet(int index, Song element) {

    }

    @Override
    protected Song doRemove(int index) {

    }
}

*/

}
