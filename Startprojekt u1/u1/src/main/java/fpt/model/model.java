package fpt.model;

import fpt.interfaces.*;
import fpt.interfaces.Song;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by corin on 09.05.2017.
 */
public class model implements fpt.interfaces.SongList {
    SongList list1 = new SongList();
    SongList list2 = new SongList();
    SongList list3 = new SongList();
    SongList list4 = new SongList();

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
}
