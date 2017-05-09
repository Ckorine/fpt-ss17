package fpt.model;

import fpt.interfaces.*;
import fpt.interfaces.Song;
import javafx.collections.ModifiableObservableListBase;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by corin on 05.05.2017.
 */
public class SongList extends ModifiableObservableListBase<Song> implements fpt.interfaces.SongList {
    @Override
    public boolean addSong(Song s) throws RemoteException {
        return false;
    }

    @Override
    public boolean deleteSong(Song s) throws RemoteException {
        return false;
    }

    @Override
    public void setList(ArrayList<Song> s) throws RemoteException {

    }

    @Override
    public ArrayList<Song> getList() throws RemoteException {
        return null;
    }

    @Override
    public void deleteAllSongs() throws RemoteException {

    }

    @Override
    public int sizeOfList() throws RemoteException {
        return 0;
    }

    @Override
    public Song findSongByPath(String name) throws RemoteException {
        return null;
    }

    @Override
    public Iterator<Song> iterator() {
        return null;
    }

    @Override
    public Song get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected void doAdd(int index, Song element) {

    }

    @Override
    protected Song doSet(int index, Song element) {
        return null;
    }

    @Override
    protected Song doRemove(int index) {
        return null;
    }
}
