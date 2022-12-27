package client;

import control.IRemoteClient;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RMIClient {
    public static void main(String[] args) {
         System.out.println("Client is running...");

        Registry registry = null;
        try {
            // Khai báo địa chỉ của máy chủ RMI
            String host = InetAddress.getLocalHost().getHostName();

            // Lấy đối tượng Registry trên máy chủ
            registry = LocateRegistry.getRegistry(host,12345);

            //  Lấy danh sách các đối tượng đang được đăng ký
            String[] names = registry.list();
            System.out.println("List name ");
            for (String name : names) {
                System.out.println(name);
            }

            // Lấy đối tượng từ xa
            /**
            registry.lookup với server khai báo bằng naming thì chỉ cần tên service VD: registry.lookup("server");
            với server khai báo bằng Registry thì cần rmi://host:port/service
             */
            IRemoteClient client = (IRemoteClient) registry.lookup(names[0]);

            // Gọi phương thức từ xa
            String ma = client.getMa();
            System.out.println("Ma SV"+ma);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}