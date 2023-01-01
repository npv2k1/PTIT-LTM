package rmi.chat;

public interface IChat extends java.rmi.Remote{
    public String diemDanh(String maSV, String hovaten) throws java.rmi.RemoteException;
}
