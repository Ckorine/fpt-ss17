package fpt.sockets;

import com.sun.corba.se.spi.activation.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by corin on 08.07.2017.
 */
public class TCPServer implements Runnable {
    private boolean run = true;
    Socket connectionSocket;
    Anmeldung anmeldung= new Anmeldung();
    public TCPServer(Socket socket){
        try{
            System.out.println("client got connected");
            this.connectionSocket=socket;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void run(){
        //server wartet auf Anmeldung auf dem Port 5020
        try ( ServerSocket serverSocket= new ServerSocket(5020)){
            while(isRunning()){
                 try {
                     serverSocket.accept();
                 }catch(Exception e1){
                     e1.printStackTrace();
                 }
                    // data lesen
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    String username=reader.readLine();
                    String password = reader.readLine();
                    //data schreiben
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
                if(username.equals(anmeldung.getUSERNAME()) &&password.equals(anmeldung.getPassword())){
                    System.out.println("Welcome, " + username);
                }else{
                    System.out.println("Login Failed");
                }
                    writer.flush();
                    writer.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public synchronized boolean isRunning() {
        return run;
    }
}
