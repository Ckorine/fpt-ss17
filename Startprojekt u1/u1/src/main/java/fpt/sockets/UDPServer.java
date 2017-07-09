package fpt.sockets;

import fpt.controller.ControllerClient;
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
public class UDPServer implements Runnable{

    private boolean run = true;

    @Override
    public void run() {
        // Socket erstellen unter dem der Server erreichbar ist
        try (DatagramSocket socket =  new DatagramSocket(5000)){
            socket.setSoTimeout(1000);

            while (isRunning()) {
                // Neues Paket anlegen
                DatagramPacket packet = new DatagramPacket(new byte[5], 5);
                // Auf Paket warten
                try {
                    socket.receive(packet);
                } catch (Exception e) {
                    continue;
                }
                // Daten auslesen
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String data = new String(packet.getData());
                data = data.trim();

                System.out.printf(
                        "%s Anfrage von %s : %d\n",
                        data, address, port);


                if (data.equals("{" +"\"cmd\"" + ":"+ "\"time\"" + "}")) {

                    byte[] b = ControllerClient.getTemps().getBytes();

                    // Paket mit neuen Daten (Datum) als Antwort vorbereiten
                    packet = new DatagramPacket(b, b.length,
                            address, port);
                    // Paket versenden
                    socket.send(packet);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public synchronized void shutdown() {
        run = false;
    }

    public synchronized boolean isRunning() {
        return run;
    }


    }

