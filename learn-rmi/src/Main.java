import rmi.chat.IChat;
import rmi.exercise.ITeacher;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//interface IChat extends java.rmi.Remote {
//    public String diemdanh(String maSV, String hovaten) throws RemoteException;
//}

public class Main  {



    public static void main(String[] args) {
        String ip = "10.20.208.251";

        try {
            Registry registry = LocateRegistry.getRegistry(ip, 1099);
            // list all the services
            String[] services = registry.list();
            for (String service : services) {
                System.out.println(service);
            }

            // get the service
            ITeacher rmiTeacher = (ITeacher) registry.lookup(services[0]);

            //ITeacher rmiTeacher = (ITeacher) registry.lookup("rmi://10.20.208.251:1099/teacher");
            System.out.println("hello");



            String res = rmiTeacher.declare("10.20.211.78", 8123, "rmi://10.20.211.78:8123/student");
            System.out.println(res);

        } catch (RemoteException e) {
            System.out.println("RemoteException");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            System.out.println("NotBoundException");
            throw new RuntimeException(e);
        }


        System.out.println("Hello world!");
    }
}