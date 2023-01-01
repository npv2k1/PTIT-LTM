
package control;
import java.rmi.*;
import java.rmi.server.*;

public class RemoteClient extends UnicastRemoteObject implements IRemoteClient {
    // Implement các phương thức từ interface IRemoteClient
    // Class luôn phải extends UnicastRemoteObject
    
    public RemoteClient() throws RemoteException {
        super();
    }
    

    // Implement phương thức
    @Override
    public String getMa() throws RemoteException {
        
        System.out.println("getMa call");
        
        return "B19DCCN479" ;
    }
    
    
}
