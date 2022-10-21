/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nguyen
 */
public class DepartmentDAO extends DAO {
    public DepartmentDAO(){
        super();
    }
    public ArrayList<Department> getAll(){
        ArrayList<Department> result = new ArrayList<Department>();
        String sql = "SELECT * FROM department";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Department rm = new Department();
                rm.setId(rs.getInt("DEPT_ID"));
                rm.setName(rs.getString("DEPT_NAME"));
                rm.setNo(rs.getString("DEPT_NO"));
                rm.setLocation(rs.getString("LOCATION"));
                result.add(rm);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void create(Department department){
        String sql = "INSERT INTO department(DEPT_ID ,DEPT_NAME, DEPT_NO, LOCATION) VALUES(?, ?, ?, ?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, department.getId());
            ps.setString(2, department.getName());
            ps.setString(3, department.getNo());
            ps.setString(4, department.getLocation());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
