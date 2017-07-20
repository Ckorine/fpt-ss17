package fpt.sockets;


import javafx.application.Platform;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by corin on 05.07.2017.
 */
public class UDPClient  {
    public boolean run;
    public static String abspielZeit = new String();
    DatagramSocket dSocket;

    public UDPClient() {

        InetAddress ia = null;
        try {
            ia = InetAddress.getByName("localhost");
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        }
        try {
            dSocket = new DatagramSocket(5000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    // Socket für den Klienten anlegen
        public void connetToServer(){
        try{

            //dSocket.setSoTimeout(120000);
            Timer timer = new Timer();
            try {
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        String command = "{" + "\"cmd\"" + ":" + "\"time\"" + "}";

                        byte buffer[] = null;
                        buffer = command.getBytes();

                        // Paket mit der Anfrage vorbereiten
                        DatagramPacket packet = null;
                        try {
                            packet = new DatagramPacket(buffer,
                                    buffer.length, InetAddress.getByName("localhost"), 3141);
                            // Paket versenden
                            dSocket.send(packet);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        byte answer[] = new byte[1024];
                        // Paket für die Antwort vorbereiten
                        packet = new DatagramPacket(answer, answer.length);
                        // Auf die Antwort warten
                        try {
                            dSocket.receive(packet);
                            abspielZeit = new String(packet.getData(), 0, packet
                                    .getLength());
                            //zeit.equals(absplZeit);
                            System.out.println(abspielZeit);
                           // System.out.println("hgghj");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        // System.out.println(new String(packet.getData(), 0, packet
                        //.getLength()));




                    }
                },0,1000);

            } catch (Exception e1) {
                e1.printStackTrace();
            }



    } catch(Exception e1) {
        e1.printStackTrace();
    }

}
    public String getZeit(){
        return abspielZeit ;
    }
}
