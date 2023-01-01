package rmi.exercise;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public interface IStudent  extends java.rmi.Remote {
    public String maSV() throws RemoteException;
    public String hovaten() throws RemoteException;
}
