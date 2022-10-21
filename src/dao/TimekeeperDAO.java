package dao;

import model.Timekeeper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TimekeeperDAO extends DAO {
    public TimekeeperDAO() {
        super();
    }

    public ArrayList<Timekeeper> getAll() {
        ArrayList<Timekeeper> result = new ArrayList<Timekeeper>();
        String sql = "SELECT * FROM timekeeper";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Timekeeper timekeeper = new Timekeeper();
                timekeeper.setId(rs.getInt("Timekeeper_Id "));
                timekeeper.setEmployeeId(rs.getInt("EMP_ID"));
                timekeeper.setDate(rs.getDate("Date_Time"));
                timekeeper.setStatus(rs.getString("In_Out").charAt(0));
                result.add(timekeeper);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Timekeeper getById(String id) {
        Timekeeper timekeeper = null;
        try {
            String sql = "SELECT * FROM timekeeper WHERE Timekeeper_Id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                timekeeper = new Timekeeper();
                timekeeper.setId(resultSet.getInt("Timekeeper_Id"));
                timekeeper.setEmployeeId(resultSet.getInt("EMP_ID"));
                timekeeper.setDate(resultSet.getDate("Date_Time"));
                timekeeper.setStatus(resultSet.getString("In_Out").charAt(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timekeeper;
    }

    public boolean update(Timekeeper timekeeper) {
        try {
            String sql = "UPDATE timekeeper SET EMP_ID = ?, Date_Time = ?, In_Out = ? WHERE Timekeeper_Id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, timekeeper.getEmployeeId());
            preparedStatement.setDate(2, (Date) timekeeper.getDate());
            preparedStatement.setString(3, String.valueOf(timekeeper.getStatus()));
            preparedStatement.setInt(4, timekeeper.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean create(Timekeeper timekeeper) {
        try {
            String sql = "INSERT INTO timekeeper(Timekeeper_Id, EMP_ID, Date_Time, In_Out) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, timekeeper.getId());
            preparedStatement.setInt(2, timekeeper.getEmployeeId());
            preparedStatement.setDate(3, (Date) timekeeper.getDate());
            preparedStatement.setString(4, String.valueOf(timekeeper.getStatus()));
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(String id) {
        try {
            String sql = "DELETE FROM timekeeper WHERE Timekeeper_Id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


