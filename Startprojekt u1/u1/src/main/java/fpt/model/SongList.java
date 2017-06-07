package fpt.model;

import fpt.interfaces.Song;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ModifiableObservableListBase;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.openjpa.lib.util.Files;

import java.io.File;
import java.io.FilenameFilter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by corin on 05.05.2017.
 */
public class SongList extends ModifiableObservableListBase<Song> implements fpt.interfaces.SongList {

    private ArrayList<Song> list = new ArrayList();

    @Override
    public boolean addSong(Song s) throws RemoteException {
        return list.add(s);
    }

    @Override
    public boolean deleteSong(Song s) throws RemoteException {
        return list.remove(s);
    }

    @Override
    public void setList(ArrayList<Song> s) throws RemoteException {
        list = s;
    }

    @Override
    public ArrayList<Song> getList() throws RemoteException {
        return list;
    }

    @Override
    public void deleteAllSongs() throws RemoteException {
        list.clear();
    }

    @Override
    public int sizeOfList() throws RemoteException {
        return list.size();
    }

    @Override
    public Song findSongByPath(String name) throws RemoteException {
        for (Song s: list){
            if(name.equals(s.getPath())){
                return s;
            }
        }
        return null;
    }

    @Override
    public Iterator<Song> iterator() {
        return list.iterator();
    }

    @Override
    public Song get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected void doAdd(int index, Song element) {
        list.add(index,element);




    }

    @Override
    protected Song doSet(int index, Song element) {
        return list.set(index,element) ;
    }

    @Override
    protected Song doRemove(int index) {
        return list.remove(index);
    }
}
