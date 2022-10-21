/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import model.Department;
import model.Employee;
import model.Timekeeper;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author nguyen
 */
public class TCPClient {

    public static void main(String[] args) throws IOException {
        System.out.println("Client started");
        try {
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
            while (true) {
                System.out.println(dataInputStream.readUTF());
                String tosend = scn.nextLine();
                dataOutputStream.writeUTF(tosend);

                // If client sends exit,close this connection
                // and then break from the while loop
                if (tosend.equals("Exit")) {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }
                switch (tosend) {
                    case "1":
                        ArrayList<Department> listDepartment = (ArrayList<Department>) objectInputStream.readObject();
                        listDepartment.forEach((department) -> {
                            System.out.println(department.getId() + " " + department.getName() + " " + department.getNo() + " " + department.getLocation());
                        });
                        break;

                    case "2":
                        Department department = new Department();
                        department.createDepartment();
                        objectOutputStream.writeObject(department);
                        String answer = dataInputStream.readUTF();
                        System.out.println(answer);
                        break;
                    case "3":
                        System.out.println("Nhap id phong ban can sua: ");
                        int id = Integer.parseInt(scn.nextLine());
                        dataOutputStream.writeInt(id);
                        Department departmentFindById = (Department) objectInputStream.readObject();
                        if (departmentFindById != null) {
                            departmentFindById.updateDepartment();
                            objectOutputStream.writeObject(departmentFindById);
                            String answerUpdate = dataInputStream.readUTF();
                            System.out.println(answerUpdate);
                        } else {
                            System.out.println("Khong tim thay phong ban");
                        }
                        break;
                    case "4":
                        System.out.println("Nhap id phong ban can xoa: ");
                        int idDelete = Integer.parseInt(scn.nextLine());
                        dataOutputStream.writeInt(idDelete);
                        String answerDelete = dataInputStream.readUTF();
                        System.out.println(answerDelete);
                        break;
                    case "5":
                        ArrayList<Employee> employees = (ArrayList<Employee>) objectInputStream.readObject();
                        employees.forEach(employee ->
                                System.out.println(employee.getId() + " " + employee.getName() + " " + employee.getNo() + " " + employee.getDate() + " " + employee.getImage() + " " + employee.getJob() + " " + employee.getSalary())
                        );
                        break;
                    case "6":
                        Employee employee = new Employee();
                        employee.createEmployee();
                        objectOutputStream.writeObject(employee);
                        String answerCreateEmployee = dataInputStream.readUTF();
                        System.out.println(answerCreateEmployee);
                        break;
                    case "7":
                        System.out.println("Nhap id nhan vien can sua: ");
                        int idEmployee = Integer.parseInt(scn.nextLine());
                        dataOutputStream.writeInt(idEmployee);
                        Employee employeeFindById = (Employee) objectInputStream.readObject();
                        if (employeeFindById != null) {
                            employeeFindById.updateEmployee();
                            objectOutputStream.writeObject(employeeFindById);
                            String answerUpdateEmployee = dataInputStream.readUTF();
                            System.out.println(answerUpdateEmployee);
                        } else {
                            System.out.println("Khong tim thay nhan vien");
                        }
                        break;
                    case "8":
                        System.out.println("Nhap id nhan vien can xoa: ");
                        int idDeleteEmployee = Integer.parseInt(scn.nextLine());
                        dataOutputStream.writeInt(idDeleteEmployee);
                        String answerDeleteEmployee = dataInputStream.readUTF();
                        System.out.println(answerDeleteEmployee);
                        break;
                    case "9":
                        ArrayList<Timekeeper> timekeepers = (ArrayList<Timekeeper>) objectInputStream.readObject();
                        timekeepers.forEach(timekeeper ->
                                System.out.println(timekeeper.getId() + " " + timekeeper.getEmployeeId() + " " + timekeeper.getDate())
                        );
                        break;
                    case "10":
                        Timekeeper timekeeper = new Timekeeper();
                        timekeeper.createTimekeeper();
                        objectOutputStream.writeObject(timekeeper);
                        String answerCreateTimekeeper = dataInputStream.readUTF();
                        System.out.println(answerCreateTimekeeper);
                        break;
                    case "11":
                        System.out.println("Nhap id timekeeper can sua: ");
                        int idTimekeeper = Integer.parseInt(scn.nextLine());
                        dataOutputStream.writeInt(idTimekeeper);
                        Timekeeper timekeeperFindById = (Timekeeper) objectInputStream.readObject();
                        if (timekeeperFindById != null) {
                            timekeeperFindById.updateTimekeeper();
                            objectOutputStream.writeObject(timekeeperFindById);
                            String answerUpdateTimekeeper = dataInputStream.readUTF();
                            System.out.println(answerUpdateTimekeeper);
                        } else {
                            System.out.println("Khong tim thay timekeeper");
                        }
                        break;
                    case "12":
                        System.out.println("Nhap id timekeeper can xoa: ");
                        int idDeleteTimekeeper = Integer.parseInt(scn.nextLine());
                        dataOutputStream.writeInt(idDeleteTimekeeper);
                        String answerDeleteTimekeeper = dataInputStream.readUTF();
                        System.out.println(answerDeleteTimekeeper);
                        break;
                    default:
                        System.out.println(dataInputStream.readUTF());
                        break;
                }
//
//                // printing date or time as requested by client
//                String received = dataInputStream.readUTF();
//                System.out.println(received);
            }

            // closing resources
            scn.close();
            dataInputStream.close();
            dataOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
