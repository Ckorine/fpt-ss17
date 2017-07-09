package fpt.sockets;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by corin on 08.07.2017.
 */
public class TCPClient {
    private boolean run = true;
    Socket connectionSocket;
    Anmeldung anmeldung= new Anmeldung();
    Socket socketclient;

    public void run() {
        InetAddress ia = null;
        try {
            ia = InetAddress.getByName("localhost");
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        }

        try (ServerSocket server = new ServerSocket(5020)) {
            while (isRunning()) {
                try {
                     socketclient = server.accept();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(socketclient.getInputStream()));
                reader.readLine();
                BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(socketclient.getOutputStream()));
                writer.flush();
                writer.close();
            }
        } catch(IOException e){
                e.printStackTrace();
            }
        }


        public synchronized boolean isRunning() {
        return run;
    }



}
