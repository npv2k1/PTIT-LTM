/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author nguyen
 */
public class Employee implements Serializable {
    static final long serialVersionUID = 2L;
    private int id;
    private String name;
    private String no;
    private Date date;
    private String image;
    private String job;
    private float salary;

    private int departmentId;
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    private ArrayList<Timekeeper> timekeepers;

    public Employee() {

    }

    public Employee(int id, String name, String no, Date date, String image, String job, float salary) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.date = date;
        this.image = image;
        this.job = job;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public ArrayList<Timekeeper> getTimekeepers() {
        return timekeepers;
    }

    public void setTimekeepers(ArrayList<Timekeeper> timekeepers) {
        this.timekeepers = timekeepers;
    }

    public void createEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập departmentId: ");
        this.departmentId = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập ID: ");
        this.id = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập tên nhân viên: ");
        this.name = sc.nextLine();
        System.out.println("Nhập số nhân viên: ");
        this.no = sc.nextLine();
        System.out.println("Nhập ngày: ");
        try {
            this.date = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Nhập ảnh: ");
        this.image = sc.nextLine();
        System.out.println("Nhập công việc: ");
        this.job = sc.nextLine();
        System.out.println("Nhập lương: ");
        this.salary = Float.parseFloat(sc.nextLine());
    }

    public void updateEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên nhân viên: ");
        this.name = sc.nextLine();
        System.out.println("Nhập số nhân viên: ");
        this.no = sc.nextLine();
        System.out.println("Nhập ngày: ");
        try {
            this.date = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Nhập ảnh: ");
        this.image = sc.nextLine();
        System.out.println("Nhập công việc: ");
        this.job = sc.nextLine();
        System.out.println("Nhập lương: ");
        this.salary = Float.parseFloat(sc.nextLine());
    }

    public static void printList(ArrayList<Employee> list){
        // print title
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "ID", "Name", "No", "Date", "Image", "Job", "Salary", "DepartmentId");
        System.out.println();
        // print list
        for (Employee employee : list) {
            System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", employee.getId(), employee.getName(), employee.getNo(), employee.getDate(), employee.getImage(), employee.getJob(), employee.getSalary(), employee.getDepartmentId());
            System.out.println();
        }
    }

    public Object[] toObject() {
        return new Object[]{this.id, this.name, this.no, this.date, this.image, this.job, this.salary, this.departmentId};
    }
}
