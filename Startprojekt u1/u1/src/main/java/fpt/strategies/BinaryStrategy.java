package fpt.strategies;

import fpt.interfaces.SerializableStrategy;
import fpt.interfaces.Song;
import fpt.model.Model;
import fpt.model.SongList;

import java.io.*;

/**
 * Created by corin on 08.06.2017.
 */
public class BinaryStrategy implements SerializableStrategy {
    private Model model;
    private fpt.model.Song song;
    private FileOutputStream songOutputStream ;
    private FileInputStream songInputStream ;
    private ObjectOutputStream objectOutputStream ;
    private ObjectInputStream objectInputStream;



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
