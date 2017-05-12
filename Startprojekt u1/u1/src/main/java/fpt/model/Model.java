package fpt.model;

/**
 * Created by corin on 09.05.2017.
 */

public class Model {
    private SongList allSongs = new SongList();
    private SongList playlist = new SongList();
    private SongList playlist1 = new SongList();


    public SongList getAllSongs() {
        return allSongs;
    }

    public SongList getPlaylist() {
        return playlist;
    }


}
