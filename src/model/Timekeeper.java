/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author nguyen
 */
public class Timekeeper implements Serializable {
    static final long serialVersionUID = 3L;
    private int id;
    private Date date;
    private char status;
    private int employeeId;

    public Timekeeper() {

    }

    public Timekeeper(int id, Date date, char status, int employeeId) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Timekeeper{" +
                "id=" + id +
                ", date=" + date +
                ", status=" + status +
                ", employeeId=" + employeeId +
                '}';
    }

    public void createTimekeeper() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter employeeId: ");
        this.employeeId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter id: ");
        this.id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter date: ");
        try {
            this.date =  new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Enter status: ");
        this.status = scanner.next().charAt(0);
    }

    public void updateTimekeeper() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id: ");
        this.id = scanner.nextInt();
        System.out.println("Enter date: ");
        this.date = new Date(scanner.next());
        System.out.println("Enter status: ");
        this.status = scanner.next().charAt(0);
        System.out.println("Enter employeeId: ");
        this.employeeId = scanner.nextInt();
    }
}
