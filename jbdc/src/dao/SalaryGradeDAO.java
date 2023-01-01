package dao;

import model.SalaryGrade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SalaryGradeDAO extends DAO{
    public SalaryGradeDAO(){
        super();
    }

    public ArrayList<SalaryGrade> getAll(){
        ArrayList<SalaryGrade> result = new ArrayList<SalaryGrade>();
        String sql = "SELECT * FROM salary_grade";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                SalaryGrade rm = new SalaryGrade();
                rm.setGrade(rs.getString("GRADE"));
                rm.setLowSalary(rs.getFloat("LOW_SALARY"));
                rm.setHighSalary(rs.getFloat("HIGH_SALARY"));
                result.add(rm);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public SalaryGrade getById(String id) {
        SalaryGrade salaryGrade = null;
        try {
            String sql = "SELECT * FROM salary_grade WHERE GRADE = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                salaryGrade = new SalaryGrade();
                salaryGrade.setGrade(resultSet.getString("GRADE"));
                salaryGrade.setLowSalary(resultSet.getFloat("LOW_SALARY"));
                salaryGrade.setHighSalary(resultSet.getFloat("HIGH_SALARY"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salaryGrade;
    }

    public boolean update(SalaryGrade salaryGrade){
        try {
            String sql = "UPDATE salary_grade SET LOW_SALARY = ?, HIGH_SALARY = ? WHERE GRADE = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setFloat(1, salaryGrade.getLowSalary());
            preparedStatement.setFloat(2, salaryGrade.getHighSalary());
            preparedStatement.setString(3, salaryGrade.getGrade());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int grade){
        try {
            String sql = "DELETE FROM salary_grade WHERE GRADE = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, grade);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
