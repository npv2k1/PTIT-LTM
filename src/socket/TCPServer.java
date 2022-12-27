/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Student;


/**
 * Thứ tự khai báo luồng stream như sau:
 *  - DataInputStream
 *  - ObjectInputStream
 *  - ObjectOutputStream
 * Client khi gửi object qua mạng phải gửi theo thứ tự trên
 *  - DataOutputStream
 *  - ObjectOutputStream
 *  - ObjectInputStream
 * Thứ tự các lần gửi nhận phải đúng thứ tự
 */

public class TCPServer {

    private ServerSocket socket = null;


    public TCPServer(int port) throws ClassNotFoundException {

        try {
            socket = new ServerSocket(port);
            System.out.println("IP" + socket.getInetAddress().getHostAddress());
            while (true) {
                Socket client = socket.accept();
                DataInputStream dataIS = new DataInputStream(client.getInputStream());
                String masv = dataIS.readUTF();
                System.out.println("Ma SV: " + masv);
                
                ObjectInputStream objectIS = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream objectOS = new ObjectOutputStream(client.getOutputStream());
                
                
                Student s = (Student) objectIS.readObject();
                System.out.println("Nhan object" + s.getMaSv());
                Answer ans = new Answer();
                ans.setStudent(s);
                ans.setSuccess(true);
                
                objectOS.writeObject(ans);  
             }

        } catch (IOException e) {
        }

    }
    public static void main(String[] args) {
        try {
            TCPServer server = new TCPServer(11310);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
