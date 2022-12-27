/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Student;
/**
 *
 * @author nguyen
 */
public class TCPClient {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Start");
        try {
            // Khởi tạo socket client
            Socket socket = new Socket(InetAddress.getLocalHost().getHostName(),11310);

            // Khởi tạo luồng đầu ra (Lưu ý là luồng này là luồng gửi dữ liệu lên server, phải đúng thứ tự như vậy)
            DataOutputStream dataOS = new DataOutputStream(socket.getOutputStream());

            // Gửi string lên server
            dataOS.writeUTF("B19DCCM479");


            // Khởi tạo đối tượng Student
            Student s = new Student();
            s.setMaSv("B19DCCN479"); 

            // Khởi tạo các luồng đối tượng (Thứ tự phải như server ) server input thì client là output và ngược lại
            ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());


            // Gửi đối tượng lên server
            objectOS.writeObject(s);
            
            
            // Nhận đối tượng từ server
            Answer ans = (Answer) objectIS.readObject();
            
            System.out.println("Student"+ ans.getStudent().getMaSv());
            System.out.println("Success" + ans.isSuccess()); 
                 
        } catch (UnknownHostException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }    
        System.out.println("Done");
        
        
    }
    
    
}
