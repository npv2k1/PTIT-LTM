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

    public Department getById(int id){
        Department department = null;
        try {
            String sql = "SELECT * FROM department WHERE DEPT_ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                department = new Department();
                department.setId(resultSet.getInt("DEPT_ID"));
                department.setName(resultSet.getString("DEPT_NAME"));
                department.setNo(resultSet.getString("DEPT_NO"));
                department.setLocation(resultSet.getString("LOCATION"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
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

    public boolean create(Department department){

        String sql = "INSERT INTO department(DEPT_ID ,DEPT_NAME, DEPT_NO, LOCATION) VALUES(?, ?, ?, ?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, department.getId());
            ps.setString(2, department.getName());
            ps.setString(3, department.getNo());
            ps.setString(4, department.getLocation());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Department department){
        String sql = "UPDATE department SET DEPT_NAME = ?, DEPT_NO = ?, LOCATION = ? WHERE DEPT_ID = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, department.getName());
            ps.setString(2, department.getNo());
            ps.setString(3, department.getLocation());
            ps.setInt(4, department.getId());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id){
        String sql = "DELETE FROM department WHERE DEPT_ID = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }






}
