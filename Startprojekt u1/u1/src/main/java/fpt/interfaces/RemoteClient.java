package fpt.interfaces;

import fpt.model.Model;
import fpt.view.ViewClient;
import fpt.view.ViewServer;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by corin on 14.07.2017.
 */
public interface RemoteClient {
    fpt.model.SongList songList() throws RemoteException;
    void playNext() throws RemoteException;
    void play() throws RemoteException;
    void link(Model model, ViewClient viewClient) throws RemoteException;
    void stopButton() throws RemoteException;
    void setStrategy(int a) throws RemoteException;
    void load() throws IOException,RemoteException;
    void save() throws IOException,RemoteException;
}
