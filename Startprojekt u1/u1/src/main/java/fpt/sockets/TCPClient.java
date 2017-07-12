package fpt.sockets;

import fpt.interfaces.MusikPlayer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.Naming;

/**
 * Created by corin on 08.07.2017.
 */
public class TCPClient {
    private String name;
    private String password;
    public TCPClient(String name,String password) throws Exception{
        this.name = name;
        this.password = password;
        byte[] nameB = name.getBytes();
        byte[] passwordB = password.getBytes();
        try(Socket serverCon = new Socket(InetAddress.getLocalHost(),5020);
            InputStream in = serverCon.getInputStream();
            OutputStream out = serverCon.getOutputStream()){

            out.write(nameB);
            out.write(passwordB);

            String dienstname = in.toString();
            MusikPlayer remote = (MusikPlayer) Naming.lookup("//localhost/" + dienstname);


        }catch (IOException ie){
                ie.printStackTrace();
        }
    }

}
