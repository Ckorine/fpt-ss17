package fpt.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by corin on 05.07.2017.
 */
public class UDPClient {
   private static String packet = "cmd" + ":" + "time";

    public static void main(String[] args) {

        try (Socket serverCon = new Socket("localhost", 5000);
             InputStream in = serverCon.getInputStream();
             OutputStream out = serverCon.getOutputStream()) {

            out.write(Integer.parseInt(returnPacket()));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String returnPacket(){
        return packet;
    }
}
