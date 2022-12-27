/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

import control.IRemoteClient;
import control.RemoteClient;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.registry.Registry;

/**
 *
 * @author nguyen
 */
public class RMIServer {

    // Khởi tạo RMIServer

    public static void main(String[] args) {
        try {
            System.out.println("Start RMI");


            // Khởi tạo đối tượng RemoteClient
            IRemoteClient server = new RemoteClient();

            // Đăng ký đối tượng RemoteClient với RMI Registry
            Registry reg = LocateRegistry.createRegistry(12345);


            //nếu dùng naming thì không cần dùng Registry

            // Đăng ký đối tượng RemoteClient sử dụng Naming => thì khỉ gửi thông tin rmi lên server chỉ cần truyền tên "server"
            try {
                Naming.rebind("rmi://localhost:12345" + "/server", server);
            } catch (MalformedURLException ex) {
                Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Đăng ký đối tượng RemoteClient sử dụng Registry => thì khỉ gửi thông tin rmi lên server phải truyền tên "rmi://localhost:12345/server"
            // try {
            //    reg.bind("rmi://localhost:12345"+"/server", server);
            // } catch (AlreadyBoundException | AccessException ex) {
            //    Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
            // } 

        } catch (RemoteException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
