package fpt.model;

import java.io.File;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import fpt.interfaces.Song;

/**
 * Created by corin on 09.05.2017.
 */

public class Model {
    private SongList allSongs = new SongList();
    private SongList playlist = new SongList();
    private  Long serialVersionUID;

    public Model() {
        try {
            serialVersionUID = IDgenerator.getNextId();
        } catch (IDOverFlowException e) {
            e.printStackTrace();
        }

    }

    public void setSongsFromDir(String directory) {
        ArrayList<Song> list = new ArrayList();
        /*File lieder = new File(directory);
        File[] filelist = lieder.listFiles();*/
        int i = 0;
        for (File f : new File(directory).listFiles()) {
            if (f.getAbsolutePath().endsWith(".mp3")) {
                //System.out.println(f.getName());
                list.add(new fpt.model.Song(++i, f.getName(), f.toURI().toString()));
            }

        }

        try {
            allSongs.setList(list);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public Song findSongById(long id){
        return allSongs.findSongByID(id);
    }
    public SongList getAllSongs() {
        return allSongs;
    }

    public SongList getPlaylist() {
        return playlist;
    }




}
