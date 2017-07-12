package fpt.sockets;

import fpt.controller.ControllerServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.rmi.Naming;
import java.rmi.Remote;

/**
 * Created by corin on 08.07.2017.
 */
public class TCPServer  {
    String password ;
    String dienstname;

    public TCPServer(String password,String dienstname) throws Exception {
        this.password = password;
        this.dienstname = dienstname;
        byte [] dienstnameB = dienstname.getBytes();
        Remote remote = new ControllerServer();
        Naming.rebind(dienstname,remote);
        try (ServerSocket server = new ServerSocket(5020);) {
            int connections = 0;
            // Timeout nach 1 Minute
            // server.setSoTimeout(60000);
            while (true) {
                try {
                    Socket socket = server.accept();
                    connections++;
                    new TCPClientThread(connections, socket,password,dienstnameB).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }



}
