/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author nguyen
 */
public class UDPServer {

    public static void main(String[] args) {
        try {

            /*
             * Here is the explanation for the code above:
             * 1. Create a DatagramSocket object (myServer) to listen on port 11310.
             * 2. Create a byte array to hold the incoming datagram.
             * 3. Create a DatagramPacket object to hold the incoming datagram.
             * 4. Receive the datagram packet from the client application.
             * 5. Create a ByteArrayInputStream object.
             * 6. Create a ObjectInputStream object.
             * 7. Read the Student object from the input stream.
             * 8. Print the Student object.
             */

            System.out.println("UDP Server runing");

            // Tạo UDP server
            DatagramSocket myServer = new DatagramSocket(11310);

            // Tạo mảng byte chứa dữ liệu
            byte[] receiveData = new byte[1024];

            // Tạo gói tin nhận
            DatagramPacket receivePackage = null;
            receivePackage = new DatagramPacket(receiveData, receiveData.length);

            // Nhận gói tin
            myServer.receive(receivePackage);

            // Tạo luồng đối tượng từ mảng byte nhận được từ gói tin clienr
            ByteArrayInputStream byteIS;
            byteIS = new ByteArrayInputStream(receiveData);

            // Tạo đối tượng đọc từ mảng byte
            ObjectInputStream objectIS = new ObjectInputStream(byteIS);

            // Đọc đối tượng
            Student s = (Student) objectIS.readObject();
            System.out.println("Student" + s.getMaSv());

        } catch (SocketException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
