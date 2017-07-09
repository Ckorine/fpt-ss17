package fpt.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by corin on 08.07.2017.
 */
public class TCPServer implements Runnable {
    private boolean run = true;
    public void run(){
        try (ServerSocket server = new ServerSocket(5020)){
             while (isRunning())  {
                 try(Socket client = server.accept();
                     InputStream in = client.getInputStream();
                     OutputStream  output = client.getOutputStream()){
                    ;
                     output.flush();


             } catch (IOException e) {
                     e.printStackTrace();
                 }

             }

        }catch (IOException e2){
            e2.printStackTrace();
        }
    }




    public synchronized boolean isRunning() {
        return run;
    }
}
