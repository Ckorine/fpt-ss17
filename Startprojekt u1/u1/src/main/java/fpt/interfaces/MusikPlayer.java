package fpt.interfaces;

import fpt.model.*;
import fpt.model.SongList;
import fpt.view.ViewClient;
import fpt.view.ViewServer;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by corin on 12.07.2017.
 */
public interface MusikPlayer extends Remote {
    fpt.model.SongList songList() throws RemoteException;
    void playNext() throws RemoteException;
    void play() throws RemoteException;
    void link(Model model, ViewServer viewServer) throws RemoteException;
    void stopButton() throws RemoteException;
    String returnZeit() throws RemoteException;
    void setStrategy(int a) throws RemoteException;
    void load() throws IOException,RemoteException;
    void save() throws IOException,RemoteException;
    String[] returnStrategies() throws RemoteException;
    void addToPlay(long id) throws RemoteException;
}
