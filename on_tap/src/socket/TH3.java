/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

import client.RMIClient;
import control.IRemoteClient;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Student;

/**
 *
 * @author nguyen
 */
public class TH3 {

    public static void main(String[] args) throws ClassNotFoundException {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(11310);
            System.out.println("IP" + socket.getInetAddress().getHostAddress());
            while (true) {
                Socket client = socket.accept();
                DataInputStream dataIS = new DataInputStream(client.getInputStream());
                String masv = dataIS.readUTF();
                System.out.println("Ma SV: " + masv);

                ObjectInputStream objectIS = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream objectOS = new ObjectOutputStream(client.getOutputStream());

                Student s = (Student) objectIS.readObject();
                System.out.println("Nhan object" + s.getMaSv());
                Answer ans = new Answer();
                ans.setStudent(s);
                ans.setSuccess(true);

                objectOS.writeObject(ans);

                // RMI client call
                try {

                    String host = InetAddress.getLocalHost().getHostName();

                    Registry registry = LocateRegistry.getRegistry(host, 12345);

                    String[] services = registry.list();
                    for (String service : services) {
                        System.out.println(service);
                    }

                    IRemoteClient rmiClient = (IRemoteClient) registry.lookup(services[0]);

                    String ma = rmiClient.getMa();
                    System.out.println("Ma SV" + ma);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                } catch (NotBoundException e) {
                    throw new RuntimeException(e);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (IOException e) {
        }

    }

}
