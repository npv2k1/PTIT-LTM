package control;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Answer;
import model.Student;

public interface IRegistration extends Remote {
  Student register(Student paramStudent) throws RemoteException;
  
  Answer answer(Answer paramAnswer) throws RemoteException;
}


/* Location:              D:\ptit\ltm\OnTap\RMI\dist\Server.jar!\control\IRegistration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */