package fpt.sockets;

import fpt.controller.ControllerClient;
import fpt.controller.ControllerServer;
import fpt.interfaces.Song;
import javafx.application.Platform;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by corin on 05.07.2017.
 */
public class UDPServer {

    DatagramSocket socket = null;
    public UDPServer(){
        try {
            socket = new DatagramSocket(5000);
            while (true) {
                // Neues Paket anlegen
                DatagramPacket packet = new DatagramPacket(new byte[5], 5);
                // Auf Paket warten
                try {
                    socket.receive(packet);
                    // Empfangendes Paket in einem neuen Thread abarbeiten
                    new UDPClientThread(packet, socket).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }

    }

}