import rmi.exercise.IStudent;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

class Student extends  UnicastRemoteObject implements IStudent, Serializable {

    protected Student() throws RemoteException {
    }

    @Override
    public String maSV() throws RemoteException{
        System.out.println("call");
        return "B19DCCN479";
    }

    @Override
    public String hovaten() throws RemoteException {
        System.out.println("call");
        return "Phạm Văn Nguyên";
    }
}
public class RMIServer {

    public static void main(String[] args) {
        try {
//            Remote stub = UnicastRemoteObject.exportObject(new Student(), 0);

            IStudent chat = new Student();
            // bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(8123);
            registry.rebind("rmi://10.20.211.78:8123/student", chat);
            System.out.println("Server ready");
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}