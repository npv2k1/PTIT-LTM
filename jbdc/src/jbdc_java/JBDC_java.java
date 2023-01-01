/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jbdc_java;

import dao.DepartmentDAO;
import model.Department;

import java.util.ArrayList;

/**
 *
 * @author nguyen
 */
public class JBDC_java {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        DepartmentDAO departmentDAO = new DepartmentDAO();

        ArrayList<Department> listDepartment = departmentDAO.getAll();

        for(Department department : listDepartment){
            System.out.println(department.getId() + " " + department.getName() + " " + department.getNo() + " " + department.getLocation());
        }


    }
    
}
