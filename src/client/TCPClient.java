/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import model.Department;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nguyen
 */
public class TCPClient {

    public static void main(String[] args) throws IOException {
        System.out.println("Client started");
        try
        {
            Scanner scn = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5000);

            // obtaining input and out streams
            DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(s.getInputStream());

            // the following loop performs the exchange of
            // information between client and client handler
            while (true)
            {
                System.out.println(dataInputStream.readUTF());
                String tosend = scn.nextLine();
                dataOutputStream.writeUTF(tosend);

                // If client sends exit,close this connection
                // and then break from the while loop
                if(tosend.equals("Exit"))
                {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }
                switch (tosend){
                    case "1":
                        ArrayList<Department> listDepartment = (ArrayList<Department>) objectInputStream.readObject();
                        listDepartment.forEach((department) -> {
                            System.out.println(department.getId() + " " + department.getName() + " " + department.getNo() + " " + department.getLocation());
                        });
                        break;
                    case "2":
                        System.out.println(dataInputStream.readUTF());
                        break;
                    case "3":
                        System.out.println(dataInputStream.readUTF());
                        break;
                    case "4":
                        System.out.println(dataInputStream.readUTF());
                        break;
                    default:
                        System.out.println(dataInputStream.readUTF());
                        break;
                }

                // printing date or time as requested by client
                String received = dataInputStream.readUTF();
                System.out.println(received);
            }

            // closing resources
            scn.close();
            dataInputStream.close();
            dataOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
