package client;
import model.Answer;
import model.Student;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        System.out.println("UDP Client is running...");
        int PORT = 11310;
        String HOST_IP = "172.18.192.1";
        String MY_IP = "172.18.192.1";
        System.out.println("Start");
        Student s = new Student("B19DCCN479","Pham Van Nguyen",MY_IP,6);
        try {
            DatagramSocket mysocket = new DatagramSocket();
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            ObjectOutput oos = new ObjectOutputStream(bStream);
            oos.writeObject(s);
            byte[] serializedMessage = bStream.toByteArray();
            InetAddress IPAddress = InetAddress.getByName(HOST_IP);
            DatagramPacket sendPacket = new DatagramPacket(serializedMessage,
                    serializedMessage.length, IPAddress, PORT);
            mysocket.send(sendPacket);
            // reciver
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new
                    DatagramPacket(receiveData, receiveData.length);
            mysocket.receive(receivePacket);
            ByteArrayInputStream bais = new
                    ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Answer ans = (Answer)ois.readObject();
            System.out.println("ans"+ans.getStudent().getHovaten());
        }catch (SocketException e){
            System.err.println(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done!");
    }
}
