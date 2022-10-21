/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.Serializable;
import java.util.Date;

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
}
