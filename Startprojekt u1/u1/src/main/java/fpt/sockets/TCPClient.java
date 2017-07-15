package fpt.sockets;

import fpt.controller.ControllerClient;
import fpt.interfaces.MusikPlayer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.Remote;

/**
 * Created by corin on 08.07.2017.
 */
public class TCPClient {
    private String name;
    private String password;
    public TCPClient(String name,String password) throws Exception{
        this.name = name;
        this.password = password;


        try{Socket serverCon = new Socket("localhost",5021);
            ObjectInputStream in = new ObjectInputStream(serverCon.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(serverCon.getOutputStream());
            out.writeObject(name);
            out.writeObject(password);
            String dienstname = (String) in.readObject();
            System.out.println(dienstname);

        }catch (IOException ie){
                ie.printStackTrace();
        }
    }

}
