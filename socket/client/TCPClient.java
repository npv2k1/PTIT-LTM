package client;


import model.Answer;
import model.Student;

import java.io.*;
import java.net.*;


public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "";
        int serverPort = 11310;
        try {

            Socket socket = new Socket(serverAddress, serverPort);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            dataOutputStream.writeUTF("B19DCCN479");
//            reciver student object
            Student student = (Student) objectInputStream.readObject();
            System.out.println(student.getMaSV());
            student.setIP(socket.getInetAddress().getHostAddress());
            student.setGroup(6);
            student.setHovaten("Pham Van Nguyen");
            objectOutputStream.writeObject(student);
            dataOutputStream.writeUTF("Pham Van Nguyen");
            dataOutputStream.writeInt(6);
            Answer ans = new Answer(student);
            objectOutputStream.writeObject(ans);






        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
