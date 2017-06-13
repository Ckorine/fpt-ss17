package fpt.serialisierungStrategies;

import fpt.interfaces.SerializableStrategy;
import fpt.interfaces.Song;
import fpt.model.Model;
import fpt.model.SongList;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by corin on 08.06.2017.
 */
public class BinäreSerialisierung implements SerializableStrategy {
    private static Model model;
    public BinäreSerialisierung(Model model2){
        this.model=model2;

    }

    public static void binaerSerial(SongList songList) throws IOException {
        songList = model.getAllSongs();
        try (FileOutputStream fos = new FileOutputStream("binSer.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(songList);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void openWriteableSongs() throws IOException {

    }

    @Override
    public void openReadableSongs() throws IOException {

    }

    @Override
    public void openWriteablePlaylist() throws IOException {

    }

    @Override
    public void openReadablePlaylist() throws IOException {

    }

    @Override
    public void writeSong(Song s) throws IOException {

    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void closeReadable() {

    }

    @Override
    public void closeWriteable() {

    }
}
