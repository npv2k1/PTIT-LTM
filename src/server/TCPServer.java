/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import dao.DepartmentDAO;
import model.Department;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author nguyen
 */


// ClientHandler class
class ClientHandler extends Thread
{
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;

    final ObjectInputStream objectInputStream;
    final ObjectOutputStream objectOutputStream;
    final Socket s;

    private DepartmentDAO departmentDAO;

    // Constructor
    public ClientHandler(Socket s) throws IOException {
        this.s = s;
        this.dataInputStream = new DataInputStream(s.getInputStream());
        this.dataOutputStream = new DataOutputStream(s.getOutputStream());
        this.objectInputStream = new ObjectInputStream(s.getInputStream());
        this.objectOutputStream = new ObjectOutputStream(s.getOutputStream());
        this.departmentDAO = new DepartmentDAO();
    }

    private String makeMenu(){
        String newLine = System.getProperty("line.separator");
        StringBuilder string = new StringBuilder();
        String array[] = {
                "Lựa chọn: ",
                "1. DS Phòng ban",
                "2. Thêm phòng ban",
                "3. Sửa phòng ban",
                "4. Xóa phòng ban",
        };

        for(int i=0 ; i < array.length ; i++){
            string.append(array[i]);
            string.append(newLine);
        }
        return string.toString();
    }


    @Override
    public void run()
    {
        String received;
        String toreturn;
        while (true)
        {
            try {

                // Ask user what he wants
                String menu = makeMenu();
                dataOutputStream.writeUTF(menu);

                // receive the answer from client
                received = dataInputStream.readUTF();

                if(received.equals("Exit"))
                {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // creating Date object
                Date date = new Date();

                // write on output stream based on the
                // answer from the client
                switch (received) {

                    case "1":
                        toreturn = "Danh sách phòng ban";
                        ArrayList<Department> departments = departmentDAO.getAll();
                        objectOutputStream.writeObject(departments);
                        break;

                    case "Date" :
                        toreturn = fordate.format(date);
                        dataOutputStream.writeUTF(toreturn);
                        break;

                    case "Time" :
                        toreturn = fortime.format(date);
                        dataOutputStream.writeUTF(toreturn);
                        break;

                    default:
                        dataOutputStream.writeUTF("Invalid input");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try
        {
            // closing resources
            this.dataInputStream.close();
            this.dataOutputStream.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

public class TCPServer {
// socket TCPServer
    private ServerSocket serverSocket = null;
    private int port = 5000;

    public TCPServer() {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void listen() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Server is running...");
        TCPServer server = new TCPServer();
        server.listen();

    }
    
}
