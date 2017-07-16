package fpt.sockets;

import fpt.controller.ControllerServer;
import fpt.model.Model;
import fpt.view.ViewClient;
import fpt.view.ViewServer;

import java.io.*;
import java.net.*;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

/**
 * Created by corin on 08.07.2017.
 */
public class TCPServer extends Thread {
    private Model model;
    private String serverPassword;
    String dienst = "musicplayer";
     String dienstname = "BLAH";
    private ArrayList<String> nameList = new ArrayList<>();
    private static final String PATH = "C:\\Users\\corin\\Desktop\\Sommersmester 2017\\FPT\\Aufgabe\\Lieder";
    private ViewClient viewClient;
    private ViewServer viewServer;

    public TCPServer(String serverPassword,Model model) throws RemoteException {
        this.model = model;
        this.serverPassword = serverPassword;
        model.addSongsFromDir(PATH);

        Remote remote = new ControllerServer(model,viewServer, viewClient);//Model is here 0, have to link

        try {
            Naming.rebind("musicplayer", remote);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        System.out.println("musicplayer started");
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
