package fpt.strategies;

import fpt.interfaces.Song;
import fpt.model.SongList;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Created by corin on 08.06.2017.
 */
public class BinDeser implements Serializable {
    public static SongList binDeser(){
        SongList songListCopy = null;
        // Deserialize object
        try (FileInputStream fis = new FileInputStream("binSer.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            songListCopy = (SongList) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for( Song s: songListCopy){
            System.out.println(s.getTitle());
        }

        return songListCopy;
    }

}
