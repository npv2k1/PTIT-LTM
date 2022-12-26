package client;

import control.IRegistration;
import model.Answer;
import model.Student;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        System.out.println("Client is running...");
//        RMI client

        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry("172.18.192.1");
            String[] names = registry.list();

            // Print the names
            System.out.println("List name ");
            for (String name : names) {
                System.out.println(name);
            }


            IRegistration client = (IRegistration) registry.lookup(names[0]);

            Student s = new Student("B19DDCN478", "Pham Van Nguyen", "192.168.1.4",6);

            Answer ans = new Answer();
            ans.setStudent(s);


            client.register(s);



        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }

    }
}
