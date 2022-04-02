package org.example.DAO;

import org.example.DTO.Staff;
import org.example.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class MySqlStaffDAO extends MySqlDAO implements StaffDAO_Interface {

    @Override
    public List<Staff> findAllStaff() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Staff> staffList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM Bar_Staff";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int staff_id = resultSet.getInt("staff_id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String staff_position = resultSet.getString("staff_position");
                double rate_per_hour = resultSet.getDouble("rate_per_hour");
                int work_hours = resultSet.getInt("work_hours");
                String email = resultSet.getString("email");
                Staff s = new Staff(staff_id, first_name, last_name, staff_position, rate_per_hour, work_hours, email);
                staffList.add(s);
            }
        } catch (SQLException e) {
            throw new DaoException("All Staff - " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllStaff() " + e.getMessage());
            }
        }
        return staffList;     // may be empty
    }

    @Override
    public Staff findStaffbyID(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Staff s = null;
        try {
            connection = this.getConnection();

            String query = "SELECT * FROM Bar_Staff WHERE staffID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int staff_id = resultSet.getInt("staff_id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String staff_position = resultSet.getString("staff_position");
                double rate_per_hour = resultSet.getDouble("rate_per_hour");
                int work_hours = resultSet.getInt("work_hours");
                String email = resultSet.getString("email");
                s = new Staff(staff_id, first_name, last_name, staff_position, rate_per_hour, work_hours, email);

            }
        } catch (SQLException e) {
            throw new DaoException("findStaffbyID() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findStaffbyID() " + e.getMessage());
            }
        }
        return s;     // reference to User object, or null value
    }

    @Override
    public String deleteById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int staffID = id;

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "DELETE FROM Bar_Staff WHERE staffID = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, staffID);

            preparedStatement.executeUpdate();

            return "Deleted Successfully";

        } catch (SQLException e) {
            throw new DaoException("findAllStaffResultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllStaff() " + e.getMessage());
            }
        }
    }
}
