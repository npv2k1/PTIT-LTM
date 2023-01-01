/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ltmtest1;


import control.IRMIServer;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;




/**
 *
 * @author nguyen
 */
public class LTMtest1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Client start");
            // TODO code application logic here
            String host = "192.168.129.100";
            int port = 16543;
            Socket client = new Socket(host,port);
            DataInputStream dataIS = new DataInputStream(client.getInputStream());
            ObjectOutputStream objectOS = new ObjectOutputStream(client.getOutputStream());
            
            Student student = new Student();
            student.setMaSV("B19DCCN479");
            student.setHovaten("Pham Van Nguyen");
            student.setIP("192.168.1.1");
            student.setGroup(6);
            
            
            objectOS.writeObject(student);
             
            objectOS.writeUTF("student1");
            objectOS.flush();
            
            int rmiPort = dataIS.readInt();
            
            System.out.println("RMI port" + rmiPort);
            
            
//            rmi client

            Registry reg = LocateRegistry.getRegistry("",rmiPort);
            IRMIServer rmiService = (IRMIServer) reg.lookup("student1");
            
            
            
          
            
            

        

            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(LTMtest1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(LTMtest1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
