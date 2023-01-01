/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author nguyen
 */
public class UDPClient {

    public static void main(String[] args) {
        try {
            System.out.println("UDP CLient run");

            // Khai báo host
            String host= InetAddress.getLocalHost().getHostName();

            // Khai báo udp socket
            DatagramSocket mysocket = new DatagramSocket(11111);

            // Khai báo object
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOS = new ObjectOutputStream(bStream);
            Student s = new Student();            
            s.setMaSv("B19DCCN479");
            objectOS.writeObject(s);            
            byte[] sendMessage = bStream.toByteArray();
            
           
            // Lấy ip
            InetAddress ip = InetAddress.getByName(host);
            
            // Tạo gói tin
            DatagramPacket sendPacket = new DatagramPacket(sendMessage, sendMessage.length,ip,11310);
            

            // Gửi gói tin
            mysocket.send(sendPacket);  
        
        } catch (SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

}
