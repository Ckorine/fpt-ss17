package fpt.model;

import java.io.File;
import fpt.interfaces.Song;

/**
 * Created by corin on 09.05.2017.
 */

public class Model {
    private SongList allSongs = new SongList();
    private SongList playlist = new SongList();

    public Model() {
        allSongs.file();

        }
    public SongList getAllSongs() {
        return allSongs;
    }

    public SongList getPlaylist() {
        return playlist;
    }

    public void songDetails(){
        for(Song song: allSongs) {
            song.getTitle();
            song.getInterpret();

        }

    }


}
