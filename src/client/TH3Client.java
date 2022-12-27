/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Student;

/**
 *
 * @author nguyen
 */
public class TH3Client {
      public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Start");
        try {
            Socket socket = new Socket(InetAddress.getLocalHost().getHostName(),11310);
            DataOutputStream dataOS = new DataOutputStream(socket.getOutputStream());
            dataOS.writeUTF("server");
            Student s = new Student();
            s.setMaSv("server");        
            
            
          
            
            ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());


            
            objectOS.writeObject(s);
            
            
            Answer ans = (Answer) objectIS.readObject();
            
            System.out.println("Student"+ ans.getStudent().getMaSv());
            System.out.println("Success" + ans.isSuccess());
            
            
            
            
            
            
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }    
        System.out.println("Done");
        
        
    }
}
