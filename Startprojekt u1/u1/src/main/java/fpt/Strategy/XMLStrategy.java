package fpt.Strategy;

import fpt.interfaces.SerializableStrategy;
import fpt.interfaces.Song;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Created by benja on 16.06.2017.
 */
public class XMLStrategy implements SerializableStrategy {
    private XMLEncoder xmlencoder;
    private XMLDecoder xmldecoder;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;

    @Override
    public void openWriteableSongs() throws IOException {
        fileOutputStream= new FileOutputStream("song.xml");
        xmlencoder=new XMLEncoder(fileOutputStream);

    }


    @Override
    public void openReadableSongs() throws IOException {
       fileInputStream=new FileInputStream("song.xml");
       xmldecoder = new XMLDecoder(fileInputStream);

    }

    @Override
    public void openWriteablePlaylist() throws IOException {
        fileOutputStream= new FileOutputStream("playliste.xml");
        xmlencoder=new XMLEncoder(fileOutputStream);


    }

    @Override
    public void openReadablePlaylist() throws IOException {
        fileInputStream=new FileInputStream("playliste.xml");
        xmldecoder = new XMLDecoder(fileInputStream);


    }

    @Override
    public void writeSong(Song s) throws IOException {
        if(s!= null && xmlencoder!=null){
            xmlencoder.writeObject(s);
            xmlencoder.flush();//schreib gepufferte Daten
        }else { throw new IOException("XMLDecoder does not exist!!!!!!!!!!!");}

    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException {
        if (xmldecoder!= null){
            return (Song) xmldecoder.readObject();
        }else{throw  new IOException("warning ! XMLDecoder DOES NOT EXIST");}

    }

    @Override
    public void closeReadable() {
    if(xmldecoder != null){
        xmldecoder.close();
        xmldecoder=null;
    }
    }

    @Override
    public void closeWriteable() {
        if(xmlencoder!=null){
            xmlencoder.close();
            xmlencoder=null;
        }

    }
}
