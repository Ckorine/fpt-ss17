package fpt.sockets;

import fpt.interfaces.Song;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * Created by corin on 05.07.2017.
 */
public class UDPServer {

    public static void main(String[] args){
        try (ServerSocket server = new ServerSocket(5000)) {

            // Timeout nach 1 Minute
            // server.setSoTimeout(60000);
            while (true) {
                try (Socket client = server.accept();
                     InputStream in = client.getInputStream();
                     OutputStream out = client.getOutputStream()) {


                    // Ergebnis auf Outputstream schreiben
                    //out.write(result);
                    out.flush();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }

    }



    }

