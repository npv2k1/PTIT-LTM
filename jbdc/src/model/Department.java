/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author nguyen
 */
public class Department implements Serializable {
    private int id;
    private String name;
    private String no;
    private String location;

    public Department(String name, String no, String location) {
        this.name = name;
        this.no = no;
        this.location = location;
    }

    public Department(int id, String name, String no, String location) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.location = location;
    }


    public Department() {
    }

    public void createDepartment(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập ID:");
        this.id = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập tên phòng ban: ");
        this.name = sc.nextLine();
        System.out.println("Nhập số phòng ban: ");
        this.no = sc.nextLine();
        System.out.println("Nhập địa điểm: ");
        this.location = sc.nextLine();
    }

    public void updateDepartment(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên phòng ban: ");
        this.name = sc.nextLine();
        System.out.println("Nhập số phòng ban: ");
        this.no = sc.nextLine();
        System.out.println("Nhập địa điểm: ");
        this.location = sc.nextLine();
    }

    public void printDepartment(){
        System.out.println("ID: " + this.id);
        System.out.println("Tên phòng ban: " + this.name);
        System.out.println("Số phòng ban: " + this.no);
        System.out.println("Địa điểm: " + this.location);
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
    public Object[] toObject() {
        return new Object[] { id, name, no, location };
    }
}
