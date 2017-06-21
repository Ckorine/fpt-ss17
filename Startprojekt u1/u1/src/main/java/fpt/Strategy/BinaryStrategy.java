package fpt.Strategy;

import fpt.interfaces.SerializableStrategy;
import fpt.interfaces.Song;

import java.io.*;
import java.util.List;

/**
 * Created by benja on 16.06.2017.
 */
public class  BinaryStrategy implements SerializableStrategy{
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Song song;
    @Override
    public void openWriteableSongs() throws IOException {
        fileOutputStream = new FileOutputStream("bin.ser");
        outputStream= new ObjectOutputStream(fileOutputStream);

    }

    @Override
    public void openReadableSongs() throws IOException {
        fileInputStream =new FileInputStream("bin.ser");
        inputStream=new ObjectInputStream(fileInputStream);
    }

    @Override
    public void openWriteablePlaylist() throws IOException {
        fileOutputStream = new FileOutputStream("play.ser");
        outputStream= new ObjectOutputStream(fileOutputStream);


    }

    @Override
    public void openReadablePlaylist() throws IOException {
        fileInputStream =new FileInputStream("play.ser");
        inputStream=new ObjectInputStream(fileInputStream);

    }

    @Override
    public void writeSong(Song s) throws IOException {
        if(s!= null && outputStream!=null){
            outputStream.writeObject(s);
            outputStream.flush();//schreib gepufferte Daten
        }else { throw new IOException("objectOutputStream does not exist!!!!!!!!!!!");}

    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException {
        if (inputStream!= null){
            while(inputStream.read() != -1) {
                song = (Song) inputStream.readObject();
            }
        }else{throw  new IOException("warning ! ObjectInputStream DOES NOT EXIST");}
            return song;
    }

    @Override
    public void closeReadable() throws IOException {

if (inputStream!=null){
    inputStream.close();
    inputStream=null;
}
    }

    @Override
    public void closeWriteable() throws IOException {
        if(outputStream!=null){
            outputStream.close();
            inputStream=null;
        }

    }
}
