package fpt.interfaces;

import java.io.IOException;
import java.rmi.Remote;

/**
 * Created by corin on 12.07.2017.
 */
public interface MusikPlayer extends Remote {

    void playNext();
    void play();
    void stopButton();
    void setStrategy(int a);
    void load() throws IOException;
    void save() throws IOException;
}
