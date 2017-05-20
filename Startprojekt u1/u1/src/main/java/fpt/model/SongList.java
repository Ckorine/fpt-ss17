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
    File lieder = new File("C:\\Users\\corin\\Desktop\\Sommersmester 2017\\FPT\\Aufgabe\\Lieder");
    File [] listOfLieder = lieder.listFiles();


    public void file(){

        for(File f: listOfLieder){
            if(f.getAbsolutePath().endsWith(".mp3")){
                System.out.println(f.getName());
                list.add(new fpt.model.Song(f.getName(), f.getAbsolutePath()));


            }

        }
    }

    public void play() {
        for (File f : listOfLieder) {
            if (f.getAbsolutePath().endsWith(".mp3")) {
                Media media = new Media(f.toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();


            }

        }
    }
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

    }

    @Override
    public ArrayList<Song> getList() throws RemoteException {
        return list;
    }

    @Override
    public void deleteAllSongs() throws RemoteException {
     list.removeAll(list);
    }

    @Override
    public int sizeOfList() throws RemoteException {
        return list.size();
    }

    @Override
    public Song findSongByPath(String name) throws RemoteException {
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
