/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.SalaryGradeDAO;
import dao.TimekeeperDAO;
import model.Department;
import model.Employee;
import model.Timekeeper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author nguyen
 */


// ClientHandler class
class ClientHandler extends Thread {
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;

    final ObjectInputStream objectInputStream;
    final ObjectOutputStream objectOutputStream;
    final Socket s;

    private DepartmentDAO departmentDAO;
    private EmployeeDAO employeeDAO;
    private TimekeeperDAO timekeeperDAO;
    private SalaryGradeDAO salaryGradeDAO;

    private void sendMessage(String message) {
        try {
            this.dataOutputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Constructor
    public ClientHandler(Socket s) throws IOException {
        this.s = s;
        this.dataInputStream = new DataInputStream(s.getInputStream());
        this.dataOutputStream = new DataOutputStream(s.getOutputStream());
        this.objectInputStream = new ObjectInputStream(s.getInputStream());
        this.objectOutputStream = new ObjectOutputStream(s.getOutputStream());
        this.departmentDAO = new DepartmentDAO();
        this.employeeDAO = new EmployeeDAO();
        this.timekeeperDAO = new TimekeeperDAO();
        this.salaryGradeDAO = new SalaryGradeDAO();
    }

    private String makeMenu() {
        String newLine = System.getProperty("line.separator");
        StringBuilder string = new StringBuilder();
        String array[] = {
                "L???a ch???n: ",
                "1. DS Ph??ng ban",
                "2. Th??m ph??ng ban",
                "3. S???a ph??ng ban",
                "4. X??a ph??ng ban",
                "5. DS Nh??n vi??n",
                "6. Th??m nh??n vi??n",
                "7. S???a nh??n vi??n",
                "8. X??a nh??n vi??n",
                "9. DS ??i l??m/ ngh???",
                "10. Th??m ??i l??m/ ngh???",
                "11. S???a ??i l??m/ ngh???",
                "12. X??a ??i l??m/ ngh???",
                "13. Exit"

        };

        for (int i = 0; i < array.length; i++) {
            string.append(array[i]);
            string.append(newLine);
        }
        return string.toString();
    }


    @Override
    public void run() {
        String received;
        String toreturn;
        while (true) {
            try {

                // Ask user what he wants
                String menu = makeMenu();
                dataOutputStream.writeUTF(menu);

                // receive the answer from client
                received = dataInputStream.readUTF();

                if (received.equals("Exit")) {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

                switch (received) {

                    case "1":
                        toreturn = "Danh s??ch ph??ng ban";
                        ArrayList<Department> departments = departmentDAO.getAll();
                        objectOutputStream.writeObject(departments);
                        break;
                    case "2":
                        Department department = (Department) objectInputStream.readObject();
                        boolean status = departmentDAO.create(department);
                        if (status) {
                            toreturn = "Th??m th??nh c??ng";
                        } else {
                            toreturn = "Th??m th???t b???i";
                        }
                        dataOutputStream.writeUTF(toreturn);
                        break;
                    case "3":
                        int departmentId = dataInputStream.readInt();
                        System.out.println("S???a ph??ng ban -> " + departmentId);
                        Department departmentById = departmentDAO.getById(departmentId);
                        objectOutputStream.writeObject(departmentById);
                        if (departmentById != null) {
                            Department departmentUpdate = (Department) objectInputStream.readObject();
                            boolean statusUpdate = departmentDAO.update(departmentUpdate);
                            if (statusUpdate) {
                                toreturn = "Th??m th??nh c??ng";
                            } else {
                                toreturn = "Th??m th???t b???i";
                            }
                            dataOutputStream.writeUTF(toreturn);
                        }
                        break;
                    case "4":
                        int departmentIdDelete = dataInputStream.readInt();
                        System.out.println("X??a ph??ng ban -> " + departmentIdDelete);
                        boolean statusDelete = departmentDAO.delete(departmentIdDelete);
                        if (statusDelete) {
                            toreturn = "X??a th??nh c??ng";
                        } else {
                            toreturn = "X??a th???t b???i";
                        }
                        dataOutputStream.writeUTF(toreturn);
                        break;
                    case "5":
                        ArrayList<Employee> employees = employeeDAO.getAll();
                        objectOutputStream.writeObject(employees);
                        break;

                    case "6":
                        Employee employee = (Employee) objectInputStream.readObject();
                        boolean statusEmployee = employeeDAO.create(employee);
                        if (statusEmployee) {
                            toreturn = "Th??m th??nh c??ng";
                        } else {
                            toreturn = "Th??m th???t b???i";
                        }
                        dataOutputStream.writeUTF(toreturn);
                        break;
                    case "7":
                        int employeeId = dataInputStream.readInt();
                        System.out.println("S???a nh??n vi??n -> " + employeeId);
                        Employee employeeById = employeeDAO.getById(employeeId);
                        objectOutputStream.writeObject(employeeById);
                        if (employeeById != null) {
                            Employee employeeUpdate = (Employee) objectInputStream.readObject();
                            boolean statusUpdate = employeeDAO.update(employeeUpdate);
                            if (statusUpdate) {
                                toreturn = "Th??m th??nh c??ng";
                            } else {
                                toreturn = "Th??m th???t b???i";
                            }
                            dataOutputStream.writeUTF(toreturn);
                        }

                        break;
                    case "8":
                        int employeeIdDelete = dataInputStream.readInt();
                        System.out.println("X??a nh??n vi??n -> " + employeeIdDelete);
                        boolean statusDeleteEmployee = employeeDAO.delete(employeeIdDelete);
                        if (statusDeleteEmployee) {
                            toreturn = "X??a th??nh c??ng";
                        } else {
                            toreturn = "X??a th???t b???i";
                        }
                        dataOutputStream.writeUTF(toreturn);
                        break;
                    case "9":
                        ArrayList<Timekeeper> timekeepers = timekeeperDAO.getAll();
                        objectOutputStream.writeObject(timekeepers);
                        break;
                    case "10":
                        Timekeeper timekeeper = (Timekeeper) objectInputStream.readObject();
                        boolean statusTimekeeper = timekeeperDAO.create(timekeeper);
                        if (statusTimekeeper) {
                            toreturn = "Th??m th??nh c??ng";
                        } else {
                            toreturn = "Th??m th???t b???i";
                        }
                        dataOutputStream.writeUTF(toreturn);
                        break;
                    case "11":
                        String timekeeperId = dataInputStream.readUTF();
                        System.out.println("S???a ??i l??m/ ngh??? -> " + timekeeperId);
                        Timekeeper timekeeperById = timekeeperDAO.getById(timekeeperId);
                        objectOutputStream.writeObject(timekeeperById);
                        if (timekeeperById != null) {
                            Timekeeper timekeeperUpdate = (Timekeeper) objectInputStream.readObject();
                            boolean statusUpdate = timekeeperDAO.update(timekeeperUpdate);
                            if (statusUpdate) {
                                toreturn = "Th??m th??nh c??ng";
                            } else {
                                toreturn = "Th??m th???t b???i";
                            }
                            dataOutputStream.writeUTF(toreturn);
                        }

                        break;
                    case "12":
                        String timekeeperIdDelete = dataInputStream.readUTF();
                        System.out.println("X??a ??i l??m/ ngh??? -> " + timekeeperIdDelete);
                        boolean statusDeleteTimekeeper = timekeeperDAO.delete(timekeeperIdDelete);
                        if (statusDeleteTimekeeper) {
                            toreturn = "X??a th??nh c??ng";
                        } else {
                            toreturn = "X??a th???t b???i";
                        }
                        dataOutputStream.writeUTF(toreturn);
                        break;


                    case "13":
                        toreturn = "Exit";
                        dataOutputStream.writeUTF(toreturn);
                        break;
                    default:
                        dataOutputStream.writeUTF("Invalid input");
                        break;
                }
            } catch (IOException e) {
//                e.printStackTrace();
                break;
//                Thread.currentThread().interrupt();

            } catch (ClassNotFoundException e) {
                break;
//                Thread.currentThread().interrupt();
//                throw new RuntimeException(e);

            }
        }

        try {
            // closing resources
            this.dataInputStream.close();
            this.dataOutputStream.close();
            this.objectInputStream.close();
            this.objectOutputStream.close();
            this.s.close();


        } catch (IOException e) {

            Thread.currentThread().interrupt()

//            e.printStackTrace();
            ;//preserve the message
            return;

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
//                e.printStackTrace();
                break;
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
