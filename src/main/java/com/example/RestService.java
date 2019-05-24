package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.example.bean.EmployeeBean;

public interface RestService {
    public default ResponseMessage<EmployeeBean> getEmployees() {
        ResponseMessage<EmployeeBean> message = new ResponseMessage<>();

        try (Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select * from employees;")) {

            message.setTimeStamp(LocalDateTime.now());
            message.setStatus("SUCCESS");
            message.setMsg(EmployeeBean.ofResultSet(res));
        } catch (SQLException e) {
            e.printStackTrace();

            message.setTimeStamp(LocalDateTime.now());
            message.setStatus("ERROR");
            message.setMsg(new ArrayList<>());
        }

        return message;
    }

    public default ResponseMessage<EmployeeBean> getEmployeeById(Integer id) {
        ResponseMessage<EmployeeBean> message = new ResponseMessage<>();
        String sql = "select * from employees where id=?;";

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();

            ArrayList<EmployeeBean> tmp = new ArrayList<>();
            tmp.add(EmployeeBean.ofSingleResult(res));

            message.setTimeStamp(LocalDateTime.now());
            message.setStatus("OK");
            message.setMsg(tmp);
        } catch (SQLException e) {
            e.printStackTrace();

            message.setTimeStamp(LocalDateTime.now());
            message.setStatus("ERROR");
            message.setMsg(new ArrayList<>());
        }

        return message;
    }

    public default ResponseMessage<EmployeeBean> deleteEmployee(Integer id) {
        ResponseMessage<EmployeeBean> message = new ResponseMessage<>();
        String sql = "delete from employees where id=?;";

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            message.setTimeStamp(LocalDateTime.now());
            message.setStatus("deleted");
            message.setMsg(new ArrayList<>());
        } catch (SQLException e) {
            e.printStackTrace();

            message.setTimeStamp(LocalDateTime.now());
            message.setStatus("error");
            message.setMsg(new ArrayList<>());
        }

        return message;
    }

    public default ResponseMessage<EmployeeBean> updateEmployee(EmployeeBean employee) {
        ResponseMessage<EmployeeBean> message = new ResponseMessage<>();
        EmployeeBean current = new EmployeeBean();
        
        String sql1 = "select * from employees where id=?;";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql1)) {
            
            stmt.setInt(1, employee.getId());
            ResultSet res = stmt.executeQuery();

            current = EmployeeBean.ofSingleResult(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        current.update(employee);
        
        String sql2 = "update employees set firstName=?,"
                    + "lastName=?,"
                    + "birthDate=?,"
                    + "gender=?,"
                    + "company=?,"
                    + "department=?,"
                    + "employerId=?,"
                    + "email=? "
                    + "where id=?;";

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql2)) {

            stmt.setString(1, current.getFirstName());
            stmt.setString(2, current.getLastName());
            stmt.setString(3, current.getBirthDate().toString());
            stmt.setString(4, current.getGender());
            stmt.setString(5, current.getCompany());
            stmt.setString(6, current.getDepartment());
            stmt.setString(7, current.getEmployerId());
            stmt.setString(8, current.getEmployerId());
            stmt.setInt(9, current.getId());

            stmt.executeUpdate();

            message.setTimeStamp(LocalDateTime.now());
            message.setStatus("Ok");

            ArrayList<EmployeeBean> tmp = new ArrayList<>();
            tmp.add(current);
            message.setMsg(tmp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return message;
    }
}