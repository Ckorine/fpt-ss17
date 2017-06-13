package fpt.model;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;

import fpt.interfaces.Song;

/**
 * Created by corin on 09.05.2017.
 */

public class Model {
    private SongList allSongs = new SongList();
    private SongList playlist = new SongList();

    public Model() {

    }

    public void setSongsFromDir(String directory) {
        ArrayList<Song> list = new ArrayList();
        /*File lieder = new File(directory);
        File[] filelist = lieder.listFiles();*/
        int i = 0;
        for (File f : new File(directory).listFiles()) {
            if (f.getAbsolutePath().endsWith(".mp3")) {
                System.out.println(f.getName());
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
