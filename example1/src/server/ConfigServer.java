/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;
/**
 *
 * @author nguyen
 */
public class ConfigServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(16543);
            System.out.println("IP" + server.getLocalSocketAddress());
            while(true){
                Socket client = server.accept();
                
                
                DataOutputStream dataOS = new DataOutputStream(client.getOutputStream());
                ObjectInputStream objectIS = new ObjectInputStream(client.getInputStream());
                
                Student s = (Student) objectIS.readObject();
                
                System.out.println("Student" + s.getMaSV());
                
                String rmiName = objectIS.readUTF();
                
                System.out.println(rmiName);
                
                dataOS.writeInt(12345);
                
                
                
                
                
                
                
                
                
                
                
                
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ConfigServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConfigServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
}
