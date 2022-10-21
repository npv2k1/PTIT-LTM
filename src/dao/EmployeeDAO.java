package dao;

import model.Department;
import model.Employee;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EmployeeDAO extends DAO{
    public EmployeeDAO(){
        super();
    }
    public ArrayList<Employee> getAll(){
        ArrayList<Employee> result = new ArrayList<Employee>();
        String sql = "SELECT * FROM employee";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("EMP_ID"));
                employee.setName(rs.getString("EMP_NAME"));
                employee.setNo(rs.getString("EMP_NO"));
                employee.setDate(rs.getDate("HIRE_DATE"));
                employee.setImage(rs.getString("IMAGE"));
                employee.setJob(rs.getString("JOB"));
                employee.setSalary(rs.getFloat("SALARY"));
                result.add(employee);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public Employee getById(int id){
        Employee employee = null;
        try {
            String sql = "SELECT * FROM employee WHERE EMP_ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                employee = new Employee();
                employee.setId(resultSet.getInt("EMP_ID"));
                employee.setName(resultSet.getString("EMP_NAME"));
                employee.setNo(resultSet.getString("EMP_NO"));
                employee.setDate(resultSet.getDate("HIRE_DATE"));
                employee.setImage(resultSet.getString("IMAGE"));
                employee.setJob(resultSet.getString("JOB"));
                employee.setSalary(resultSet.getFloat("SALARY"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public boolean create(Employee employee) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sql = "INSERT INTO employee(EMP_ID, EMP_NAME, EMP_NO, HIRE_DATE, IMAGE, JOB, SALARY, DEPT_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, employee.getId());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getNo());
            ps.setString(4, sdf.format(employee.getDate()));
            ps.setString(5, employee.getImage());
            ps.setString(6, employee.getJob());
            ps.setFloat(7, employee.getSalary());
            ps.setInt(8, employee.getDepartmentId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Employee employee) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sql = "UPDATE employee SET EMP_NAME = ?, EMP_NO = ?, HIRE_DATE = ?, IMAGE = ?, JOB = ?, SALARY = ?, DEPT_ID = ? WHERE EMP_ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getNo());
            ps.setString(3, sdf.format(employee.getDate()));
            ps.setString(4, employee.getImage());
            ps.setString(5, employee.getJob());
            ps.setFloat(6, employee.getSalary());
            ps.setInt(7, employee.getDepartmentId());
            ps.setInt(8, employee.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM employee WHERE EMP_ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
