package fpt.sockets;

import fpt.controller.ControllerServer;

import java.io.*;
import java.net.*;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

/**
 * Created by corin on 08.07.2017.
 */
public class TCPServer extends Thread {
    private String serverPassword;
    String dienst = "musicplayer";
     String dienstname = "BLAH";
    private ArrayList<String> nameList = new ArrayList<>();

    public TCPServer(String serverPassword){
        this.serverPassword = serverPassword;
    }

    public void run(){
        try(ServerSocket server = new ServerSocket(5021) ){
                while(true){
                    try{Socket client = server.accept();
                        InputStream in = client.getInputStream();
                        OutputStream out = client.getOutputStream();
                        ObjectOutputStream oout = new ObjectOutputStream(out);
                        ObjectInputStream ois = new ObjectInputStream(in);
                        String clientName = (String) ois.readObject();
                        String password = (String) ois.readObject();
                        if(clientName!=null && password.equals(serverPassword)){
                            synchronized (clientName) {
                                nameList.add(clientName);
                                oout.writeObject(dienstname);
                            }
                        }else{
                            String error = "wrong password or name";
                            oout.writeObject(error.getBytes());
                        }
                        oout.flush();
                    }catch (IOException ie){
                            ie.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }catch (IOException ie2){
            ie2.printStackTrace();
        }
    }


}
