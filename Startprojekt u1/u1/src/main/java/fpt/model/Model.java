package fpt.model;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;

import fpt.interfaces.Song;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Created by corin on 09.05.2017.
 */

public class Model {
    private SongList allSongs = new SongList();
    private SongList playlist = new SongList();
    private ArrayList<Song> list = new ArrayList();
    public ArrayList<Song> getList(){
        return list;
    }

    public Model() {

        try {
            allSongs.file();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    File lieder = new File("C:\\Users\\corin\\Documents\\GitHub\\fpt-ss17\\Startprojekt u1\\u1\\src\\res\\files");
    File [] listOfLieder = lieder.listFiles();


    public SongList file() throws RemoteException {
        SongList sl = new SongList();
        for (File f : listOfLieder) {
            if (f.getAbsolutePath().endsWith(".mp3")) {
                System.out.println(f.getName());
                list.add(new fpt.model.Song(f.getName(), f.getAbsolutePath()));
                sl.setList(getList());

            }

        }
        return sl;
    }
    public void play() {

    }
    public SongList getAllSongs() {
        return allSongs;
    }

    public SongList getPlaylist() {
        return playlist;
    }





}
