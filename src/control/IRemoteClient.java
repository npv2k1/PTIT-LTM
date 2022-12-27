
package control;
import java.rmi.*;

// Creating a remote interface.
public interface IRemoteClient extends Remote {

    // Đây là 1 interface dùng cho RMI;
    // Interface luôn phải extends Remote   


    // Phương thức từ xa luôn phải throws RemoteException
    public String getMa() throws RemoteException;    
}
